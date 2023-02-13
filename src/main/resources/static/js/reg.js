
window.onload = function () {
    var email = document.getElementById("email");
    var sp1 = document.getElementById("sp1");
    /*var sp2=document.getElementById("sp2");*/
    email.onchange = function () {
        var email1 = this.value;
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (reg.test(email1)) {
            sp1.className = "sp";
            sp1.innerHTML = "邮箱格式正确";
        } else {
            sp1.className = "sp_false";
            sp1.innerHTML = "邮箱格式不正确!";
        }
    }
}
    ifsub = function () {
        let pwd = document.getElementById("pwd").value;
        let ispwd = document.getElementById("ispwd").value;
        let reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        let email=document.getElementById("email").value;
        let sp1 = document.getElementById("sp1");
        let flag=false;
        if (pwd == ispwd) {
            if(!reg.test(email)){
                alert("邮箱格式不正确!")
                return false;
            }else {
                $.ajax({
                    type:"post",
                    data:{email:email},
                    url:"/zym/users/ifEmail",
                    dataType:"text",
                    async:false,
                    success:function(data) {
                        if (data.trim() == 'F') {
                            sp1.className = "sp_false";
                            sp1.innerHTML = "邮箱已经被占用!";
                        }else {
                            flag=true;
                        }
                    }
                })
                return flag;
            }
        } else {
            alert("两次密码不一致!")
            return false;
        }
    }
