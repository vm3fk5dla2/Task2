const express = require('express');
const app = express();
var bodyParser = require("body-parser");

var async = require('async');
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:3000/testing_storeImg');
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function callback () {
  console.log("mongo db connection OK.");
});

// app.use(bodyParser.urlencoded({
//     extended: true
// }));
// app.use(bodyParser.json());

app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true, parameterLimit: 50000 }));

var imgSchema = mongoose.Schema({
    'name': String,
    'imgdata': String,
    'imgtype': String
});

var Image = mongoose.model('image', imgSchema)

app.post('/imgpost', (req, res) => {
    console.log(req.body);
    var newImage = new Image();
    newImage.name = req.body.name;
    newImage.imgdata = req.body.imgdata.toString('base64');
    //newImage.imgdata = req.body.imgdata;
    newImage.imgtype = req.body.imgtype;
    newImage.save()
    console.log("Saved!")
});

app.listen(2040, () => {
  console.log('Example app listening on port 2040!');
});