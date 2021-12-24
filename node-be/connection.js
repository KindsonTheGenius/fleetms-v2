const {createPool} = require('mysql')

const pool = createPool({
    host: 'localhost',
    user: 'root',
    password: 'rootuser',
    connectionLimit: 50
})

module.exports = pool
