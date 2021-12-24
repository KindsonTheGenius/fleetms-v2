const express = require('express');
const client = require('./connection.js')

const app = express();

app.listen(3000, () => {
    console.log('Server is listening at port 3000...');
})

app.get('/countries', (req, res) => {
    console.log('A user just requested list of countries');

    client.query(`Select * from fleetdb.Country`, (err, result) => {
        if (err) {
            console.log(err.message);
        } else {
            console.log(result)
            res.send(result);
        }
    })
})
