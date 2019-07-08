const express = require('express');
const app = express();

var async = require('async');
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:3000/testing_storeImg');
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function callback () {
  console.log("mongo db connection OK.");
});

var imgSchema = mongoose.Schema({
    name: String,
    imgdata: String,
    imgtype: String
});

var imageSchema = mongoose.model('image', imgSchema);

var imageList;

app.post('/post', (req, res) => {
    imageSchema.find(function(error, images){
        imageList = images;
    });
    res.json(imageList);
});

app.listen(3800, () => {
  console.log('Example app listening on port 3800!');
});