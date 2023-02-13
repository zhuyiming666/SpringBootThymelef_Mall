
function editUsersMsg(){
   /* let data=$("#form_edit_msg").serialize();*/
   /* let usersMessage = formToJson (data);*/
    let id=$("input[name='id']").val();
    let name=$("input[name='name']").val();
    let phone=$("input[name='phone']").val();
    let birth=$("input[name='birth']").val();
    let sex = $("input[name='sex']:checked").val();
    let usersMessage={
        "id":id,
        "name":name,
        "phone":phone,
        "birth":birth,
        "sex":sex
    }
   $.ajax({
       type:"post",
       url:"/zym/usersMsg/editMsg",
       dataType:"json",
       contentType:'application/json;charset=utf-8',  //默认"application/x-www-form-urlencoded; charset=utf-8"
       data:JSON.stringify(usersMessage),
       success:function (data){
           if(data.editStatus==1){
               layer.alert("编辑成功!",{icon:1},function () {
                   window.location.reload();
               });
           }else{
               layer.alert('编辑失败!',{icon:5});
           }
       },
       error:function (error){
           layer.alert('系统异常'+error.status,{icon:5});
       }

   })
}

function formToJson (data) {
    data = data.replace(/&/g, "\",\"");
    data = data.replace(/=/g, "\":\"");
    data = "{\"" + data + "\"}";
    return data;
}

function uploadAvatar() {
    let formData = new FormData();
    formData.append("file", $("#file1")[0].files[0]);
    $.ajax({
        type: "post",
        url:"/zym/usersMsg/changeAvatar",
        data:formData,
        contentType:false,
        processData:false,
        dataType:"json",
        mimeType:"multipart/form-data",
        success:function (data){
           if(data.status=='T'){
               layer.alert(data.msg,{icon:1},function (){
                   location.reload();
               });
           }
           if(data.status=='F'){
               layer.alert(data.msg,{icon:5},function (){
                   location.reload();
               });
           }
        },
        error:function (errorStatus) {
            layer.alert("系统异常"+errorStatus.status,{icon:5});
        }
    })
}

function updatePassword(){
    let password=$("input[name='password']").val();
    let newPassword=$("input[name='newPassword']").val();
    let ifPassword=$("input[name='ifPassword']").val();
    let checkCode=$("input[name='checkCode']").val();
    let myRegularExpression=/(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/;

    if(!myRegularExpression.test(password)||!myRegularExpression.test(newPassword)||!myRegularExpression.test(ifPassword)){
        layer.alert('请按规定输入',{icon:7,title:'提示'});
        return;
    }
    if(password==newPassword){
        layer.alert('原密码与新密码相同',{icon:7,title:'提示'});
        return;
    }
    if(ifPassword!=newPassword){
        layer.alert('两次输入的新密码不一致!',{icon:2,title:'提示'});
        return;
    }

    $.ajax({
        type:"post",
        url:"/zym/usersMsg/updatePassword",
        data:{"password":password,"newPassword":newPassword,"checkCode":checkCode},
        dataType:"json",
        success:function (data){
            if (data.status=='T') {
                layer.alert(data.msg, {icon: 6, title: '提示'});
            }
            if (data.status=='F') {
                layer.alert(data.msg, {icon: 5, title: '提示'});
            }
        },
        error:function (errorStatus) {
            layer.alert("系统异常"+errorStatus.status,{icon:5,title:'错误信息'});
        }
    });
}

