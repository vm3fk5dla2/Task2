var router = require('express').Router()
var mongoose = require('mongoose')
var Image = mongoose.model('image')
var fs = require('fs')
var multer = require('multer')

router.get('/', function (req, res, next) {
    Image.find({}).then(function (results) {
        console.log(results)
        var images = results
        // var imagesCount = results[1]
        res.render('index.ejs', {
            images: images.map(function (image) {
                return image.name
            })
        })    
    })
})

router.get('/imgupload', function (req, res, next) {
    res.render('upload.ejs', {})
})

router.get('/img/:filename', function (req, res, next) {
    Image.findOne({ name: req.params.filename }).then(function (image) {
        res.send(image.imgdata)
    })
})

var upload = multer({
    dest: './uploads/',
    limits: { fileSize: 5 * 10000 * 10000 },
    rename: function (fieldname, filename) {
        return filename.replace(/\W+/g, '-').toLowerCase()
    }
})
router.post('/imgpost', upload.single('img'), function (req, res, next) {
    console.log(req.file)
    var newImage = new Image()
    newImage.name = req.file.originalname
    newImage.imgdata = fs.readFileSync(req.file.path).toString('base64')
    newImage.imgtype = req.file.mimetype
    newImage.save()
    res.redirect(303, '/')
})

router.get('/*', function (req, res, next) {
    return res.sendStatus(404)
})

module.exports = router