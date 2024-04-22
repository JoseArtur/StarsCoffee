const express = require('express');
const axios = require('axios');
const session = require('express-session');
const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended: true }));
app.use(express.static('public'));
app.use(
    session({
        secret: 'your-secret-key', // Change this to a strong and secure key
        resave: false,
        saveUninitialized: true,
    })
);

// Middleware to check if the user is authenticated
const isAuthenticated = (req, res, next) => {
    if (req.session && req.session.token) {
        return next();
    } else {
        res.redirect('/home');
    }
};

// Define a route to render the login page
app.get('/home', (req, res) => {
    res.render('home', {success : req.query.success});
});

app.post('/get-foods', async (req, res) => {
    try {
        // Basic Authentication credentials
        const username = 'user';
        const password = 'password';

        // Base64 encode the credentials
        const authHeader = `Basic ${Buffer.from(`${username}:${password}`).toString('base64')}`;

        // Request headers
        const headers = {
            'Authorization': authHeader
        };

        // Make request to fetch foods
        const response = await axios.get('http://localhost:8090/coffee-shop/get-foods', { headers });

        // Send the foods in the response
        res.json(response.data);
    } catch (error) {
        // Handle errors
        console.error('Error fetching foods:', error);
        res.status(error.response.status || 500).send(error.response.data || 'Error fetching foods');
    }
});



// Start the server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}/home`);
});
