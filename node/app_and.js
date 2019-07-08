const express = require('express');
const app = express();

// let users = [{id: 1, name: 'alice'}, {id: 2, name: 'bek'}, {id: 3, name: 'chris'}]

// var http = require('http');

// var server = http.createServer(function (req, res) {
//   res.writeHead(200, { 'Content-Type' : 'text/plain' });
//   res.end('Hello World')
// });

var async = require('async');
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:3000/test'); // 기본 설정에 따라 포트가 상이 할 수 있습니다.
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function callback () {
  console.log("mongo db connection OK.");
});

var testSchema = mongoose.Schema({
    phNum : 'string',
    name : 'string',
    photo_id : 'number',
    person : 'number',
    contact_id : 'string',
    address : 'string'
});

var Student = mongoose.model('Schema', testSchema);

// Student 객체를 new 로 생성해서 값을 입력
// var newStudent = new Student({name:'Hong Gil Dong', address:'서울시 강남구 논현동', age:'22'});

var studentList;


app.post('/post', (req, res) => {
   // console.log(‘who get in here post /users’)
    var inputData;
    req.on('data', (data) => {
    inputData = JSON.parse(data);
    var ContactStudent = new Student({phNum:inputData.phNum, name:inputData.name, photo_id:inputData.photo_id, person:inputData.person, contact_id:inputData.contact_id, address:inputData.address});
    ContactStudent.save(function(error, data){
        if(error){
            console.log("error : no inputs!");
        }else{
            console.log('Saved!');
        }
    });
    });
    req.on('end', () => {
    console.log("phNum : " + inputData.phNum + " , name : " + inputData.name + " , address : " + inputData.address);
    });
    res.write("OK!");
    res.end();
});


app.get('/get_students', (req, res) => {
    console.log('who get in here/get_students');
    Student.find(function(error, students){
    console.log('--- Read all ---');
        if(error){
            console.log(error);
        }else{
            console.log(students);
            res.json(students)
        }
    });
});


// async.waterfall([
//     function(callback){
//         app.post('/post', (req, res) => {
//            // console.log(‘who get in here post /users’)
//            var inputData;
//            req.on('data', (data) => {
//             inputData = JSON.parse(data);
//             var ContactStudent = new Student({phNum:inputData.phNum, name:inputData.name, photo_id:inputData.photo_id, person:inputData.person, contact_id:inputData.contact_id, address:inputData.address});
//             ContactStudent.save(function(error, data){
//             if(error){
//                 console.log("error : no inputs!");
//             }else{
//                 console.log('Saved!');
//             }
//         });
//            });
//            req.on('end', () => {
//             console.log("phNum : " + inputData.phNum + " , name : " + inputData.name + " , address : " + inputData.address);
//            });
//            res.write("OK!");
//            res.end();
//         });
//         // callback(null);
//     },
//     function(callback){
//         Student.find(function(error, students){
//             console.log('--- Read all ---');
//             if(error){
//                 console.log(error);
//             }else{
//                 console.log(students);
//                 studentList = students;
//                 callback(null);
//             }
//         });
//     },
//     function(callback){
//         Student.findOne({_id:'5d1d779c75e9fd28d47438bf'}, function(error,student){
//             console.log('--- Read one ---');
//             if(error){
//                 console.log(error);
//             }else{
//                 console.log(student);
//                 callback(null);
//             }
//         });
//     }], function(err) { console.log('error:', err); });

// port : 3500으로의 연결
app.listen(3500, () => {
  console.log('Example app listening on port 3500!');
});


// android로 데이터 송신
// app.get(‘/users’, (req, res) => {
//    console.log(‘who get in here/users’);
//    res.json(users)
// });
// app.post('/post', (req, res) => {
//    // console.log(‘who get in here post /users’)
//    var inputData;
//    req.on('data', (data) => {
//      inputData = JSON.parse(data);
//    });
//    req.on('end', () => {
//      var ContactStudent = new Student({phNum:inputData.phNum, name:inputData, address:inputData});
//      console.log("phNum : " + inputData.phNum + " , name : " + inputData.name + " , address : " + inputData.address);
//    });
//    res.write("OK!");
//    res.end();
// });


// // android로 데이터 전송
// app.post('/post', (req, res) => {
//     res.json(studentList);
// });


// 9. 데이터 저장
// newStudent.save(function(error, data){
//     if(error){
//         console.log("error : no inputs!");
//     }else{
//         console.log('Saved!')
//     }
// });

// // 10. Student 레퍼런스 전체 데이터 가져오기

// Student.find(function(error, students){
//     console.log('--- Read all ---');
//     if(error){
//         console.log(error);
//     }else{
//         console.log(students);
//     }
// });


// // 11. 특정 아이디값 가져오기
// Student.findOne({_id:'585b777f7e2315063457e4ac'}, function(error,student){
//     console.log('--- Read one ---');
//     if(error){
//         console.log(error);
//     }else{
//         console.log(student);
//     }
// });


// 12. 특정아이디 수정하기
// Student.findById({_id:'5d1cac56d189f41bd8718d11'}, function(error,student){
//     console.log('--- Update(PUT) ---');
//     if(error){
//         console.log(error);
//     }else{
//         student.name = '--modified--';
//         student.save(function(error,modified_student){
//             if(error){
//                 console.log(error);
//             }else{
//                 console.log(modified_student);
//             }
//         });
//     }
// });

// // 13. 삭제
// Student.remove({_id:'5d1cac56d189f41bd8718d11'}, function(error,output){
//     console.log('--- Delete ---');
//     if(error){
//         console.log(error);
//     }

//      // ( SINCE DELETE OPERATION IS IDEMPOTENT, NO NEED TO SPECIFY )
//      //    어떤 과정을 반복적으로 수행 하여도 결과가 동일하다. 삭제한 데이터를 다시 삭제하더라도, 존재하지 않는 데이터를 제거요청 하더라도 오류가 아니기 때문에
//      //    이부분에 대한 처리는 필요없다. 그냥 삭제 된것으로 처리
        
//     console.log('--- deleted ---');
// });

// testSchema.methods.speak = function () {
//   var greeting = this.name
//   ? "Meow name is " + this.name
//   : "I don't have a name"
//   console.log("speak() - " + greeting);
// }


// var TestModel = mongoose.model("TestModel", testSchema);

// var testIns = new TestModel({ name: "testIns"});

// testIns.save(function(err, testIns){
//   if(err) return console.error(err);
//   testIns.speak();
// });

// TestModel.find(function(err, models){
//   if(err) return console.error(err);
//   console.log("find() - "+models);
// });

// TestModel.find({name:/^testIns/});