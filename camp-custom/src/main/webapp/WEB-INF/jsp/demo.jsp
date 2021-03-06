<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>HTML5大文件分片上传示例</title>

<script src="/camp-custom/plugin/jQuery/jquery.min.js"></script>
<script type="text/ecmascript" src="md5.js"></script>
<script>

	var i = -1;
	var succeed = 0;
	var databgein;  //开始时间
    var dataend;    //结束时间
    var action=false;    //false检验分片是否上传过(默认); true上传文件
    var page = {
        init: function(){
            $("#upload").click(function(){
            databgein=new Date();
            var file = $("#file")[0].files[0];  //文件对象
			upload(file);
            //isUpload(file);
            });
        }
    };
    $(function(){
        page.init();
    });
    function isUpload (file) {
                //构造一个表单，FormData是HTML5新增的
                var form = new FormData();
				var r = new FileReader();
				r.readAsBinaryString(file);
				$(r).load(function(e){
                var bolb = e.target.result;
				var md5 = '97027eb624f85892c69c4bcec8ab0f11';
                form.append("md5", md5);
                //Ajax提交
                $.ajax({
                    url: "http://192.168.1.160:8402/camp-custom/checkFile",
                    type: "POST",
                    data: form,
                    async: true,//异步
                    processData: false,//很重要，告诉jquery不要对form进行处理
                    contentType: false,//很重要，指定为false才能形成正确的Content-Type
                    success: function(data){
                    var dataObj = eval("("+data+")");
					var uuid = dataObj.fileId;
					var date = dataObj.date;
						if (dataObj.flag == "1") {
							//没有上传过文件
                         upload(file,uuid,md5,date);
                        } else if(dataObj.flag == "2") {
                        //已经上传部分
                        upload(file,uuid,md5,date);
                } else if(dataObj.flag == "3") {
                        //文件已经上传过
                        alert("文件已经上传过,秒传了！！");
                        $("#uuid").append(uuid);
                }
                    },error: function(XMLHttpRequest, textStatus, errorThrown) {
                      alert("服务器出错!");
                    }
                });
               })
    }
    /*
     * file 文件对象
     * uuid 后端生成的uuid
     * filemd5 整个文件的md5
     * date  文件第一个分片上传的日期(如:20170122)
    */
    function upload (file,uuid,filemd5,date) {
    var name = file.name;//文件名
    var size = file.size;//总大小
    var shardSize = 2 * 1024 * 1024,//以2MB为一个分片
    shardCount = Math.ceil(size/shardSize);//总片数
        if (i > shardCount) {
            i = -1;
            i = shardCount;
        } else {
            if(!action){
            i += 1;//只有在检测分片时,i才去加1; 上传文件时无需加1
            }
        }
    			//计算每一片的起始与结束位置
                var start = i * shardSize,
                end = Math.min(size, start + shardSize);
                //构造一个表单，FormData是HTML5新增的
                var form = new FormData();
                if(!action){
                form.append("action", "check");  //检测分片是否上传
                $("#param").append("action==check ");
                }else{
                form.append("action", "upload");//直接上传分片

                form.append("data", file.slice(start,end));  //slice方法用于切出文件的一部分
                $("#param").append("action==upload ");
                }
                data = file.slice(start,end);
                form.append("uuid", uuid);
                form.append("filemd5", filemd5);
                form.append("date", date);
                form.append("name", name);
                form.append("size", size);
                form.append("total", shardCount);//总片数
                form.append("index", i+1);//当前是第几片
                var ssindex = i+1;
                $("#param").append("index=="+ssindex+"<br/>");
               //按大小切割文件段
               var data = file.slice(start, end);
               var r = new FileReader();
				r.readAsBinaryString(data);
               $(r).load(function(e){

				var bolb = e.target.result;
				var md5 = '97027eb624f85892c69c4bcec8ab0f11';
                form.append("md5", md5);
                form.append("bolb", bolb);
                //Ajax提交
                $.ajax({

                    url: "http://192.168.1.160:8402/camp-custom/uploads",

                    type: "POST",

                    data: form,

                    async: true,        //异步

                    processData: false,  //很重要，告诉jquery不要对form进行处理

                    contentType: "multipart/form-data;boundary=97027eb624f85892c69c4bcec8ab0f11",  //很重要，指定为false才能形成正确的Content-Type

                    success: function(data){

                        var dataObj = eval("("+data+")");

                        var fileuuid = dataObj.fileId;

                        var flag = dataObj.flag;

                        //服务器返回该分片是否上传过
                        if(!action){

                           if (flag == "1") {
                            //未上传
                            action = true;
							} else if(dataObj.flag == "2") {
                            //已上传
                     	action = false;
                             ++succeed;
					}

                           //递归调用                　       
               	upload(file,uuid,filemd5,date);

                        }else{
                        //服务器返回分片是否上传成功

                           //改变界面
                           ++succeed;

                           $("#output").text(succeed + " / " + shardCount);

                           if (i+1 == shardCount) {

                             dataend=new Date();

                            $("#uuid").append(fileuuid);

                            $("#usetime").append(dataend.getTime()-databgein.getTime());

                           } else {                        
                             //已上传成功,然后检测下一个分片
                        action = false;
                             //递归调用                　       
                upload(file,uuid,filemd5,date);

               }

                        }   

                    },error: function(XMLHttpRequest, textStatus, errorThrown) {

                      alert("服务器出错!");

                    }

                });

               })
    }
</script>
</head>

<body>
	<input type="file" id="file" />
	<button id="upload">上传</button>
</body>

</html>