

    function btn_login() {
        let p1_error=document.getElementById("p1_error");
        $.ajax({
            type:"post",
            data:$("#login_form").serialize(),
            url:"/zym/users/login",
            dataType:"text",
            success:function (data) {
                if(data.trim()=='T'){
                    layer.alert("登录成功!",{icon:1},function () {
                        location.href="http://localhost:8080/zym/index";
                    });
                    return;
                }
                if(data.trim()=='F'){
                    layer.alert("<h4>账号或密码错误!</h4>",{icon:2});
                    p1_error.innerHTML="<h4>账号或密码错误!</h4>";
                    jQuery("#p1_error").show().delay(3000).hide(0);
                    return;
                }else if(data.trim()=='0F'){
                    layer.alert("<h4>账号或密码错误!</h4>",{icon:5});
                    $("#p1_error").html("账号已被冻结!");
                    return;
                }
            },
            error:function (status){
                layer.alert("系统异常"+status.status,{icon:2});
            }
        })
    }

    function PublicifLogin() {
        let flag=true;
        $.ajax({
            type:"post",
            url:"/zym/ifLoginExits",
            dataType:"text",
            async:false,
            success:function (data) {
                if (data.trim()=='T'){
                    flag=true;
                }else {
                    flag=false;
                }
            },
            error:function (errorStatus) {
                return false;
            }
        })
        return flag;
    }

    function usersMessage(id){

         if(PublicifLogin()){
            location.href="http://localhost:8080/zym/usersMsg/mygxin.html";
         }else {
             alert("请先登录");
         }
    }

    function loginOut() {
        if(confirm("确定要退出登录吗?")){
            $.ajax({
                type:"post",
                url:"/zym/users/loginOut",
                dataType:"text",
                success:function (data) {
                    if (data.trim()=='T'){
                        window.location.replace("http://localhost:8080/zym/index.html");
                        /*location.href="index.html";*/
                    }
                },
                error:function (errorStatus) {
                    layer.alert("系统出错!"+errorStatus.status,{icon:5});
                }
            })
        }
    }

