var mongoose = require('mongoose')
var Schema = mongoose.Schema

// example schema
var imgSchema = new Schema({
	name: String,
	imgdata: String,
	imgtype: String
})

module.exports = mongoose.model('image', imgSchema)