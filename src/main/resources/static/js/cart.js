$(function () {
    $(".num .sub").click(function () {
        var c = parseInt($(this).siblings("span").text());
        if (c <= 1) {
            $(this).attr("disabled", "disabled")
        } else {
            c--;
            $(this).siblings("span").text(c);
            var d = $(this).parents(".number").prev().text().substring(1);
            $(this).parents(".th").find(".sAll").text("￥" + (c * d).toFixed(2));
            a();
            b()
        }
    });
    $(".num .add").click(function () {
        var c = parseInt($(this).siblings("span").text());
        if (c >= 5) {
            confirm("限购5件")
        } else {
            c++;
            $(this).siblings("span").text(c);
            var d = $(this).parents(".number").prev().text().substring(1);
            $(this).parents(".th").find(".sAll").text("￥" + (c * d).toFixed(2));
            a();
            b()
        }
    });

    function a() {
        var c = 0;
        var d = $(".th input[type='checkbox']:checked").length;
        if (d == 0) {
            $("#all").text("￥" + parseFloat(0).toFixed(2))
        } else {
            $(".th input[type='checkbox']:checked").each(function () {
                var e = $(this).parents(".pro").siblings(".sAll").text().substring(1);
                c += parseFloat(e);
                $("#all").text("￥" + c.toFixed(2))
            })
        }
    }

    function b() {
        var e = 0;
        var c = $(".th input[type='checkbox']:checked").parents(".th").find(".num span");
        var d = c.length;
        if (d == 0) {
            $("#sl").text(0)
        } else {
            c.each(function () {
                e += parseInt($(this).text());
                $("#sl").text(e)
            })
        }
        if ($("#sl").text() > 0) {
            $(".count").css("background", "#c10000")
        } else {
            $(".count").css("background", "#8e8e8e")
        }
    }

    $("input[type='checkbox']").on("click", function () {
        var f = $(this).is(":checked");
        var e = $(this).hasClass("checkAll");
        if (f) {
            if (e) {
                $("input[type='checkbox']").each(function () {
                    this.checked = true
                });
                b();
                a()
            } else {
                $(this).checked = true;
                var c = $("input[type='checkbox']:checked").length;
                var d = $("input").length - 1;
                if (c == d) {
                    $("input[type='checkbox']").each(function () {
                        this.checked = true
                    })
                }
                b();
                a()
            }
        } else {
            if (e) {
                $("input[type='checkbox']").each(function () {
                    this.checked = false
                });
                b();
                a()
            } else {
                $(this).checked = false;
                var c = $(".th input[type='checkbox']:checked").length;
                var d = $("input").length - 1;
                if (c < d) {
                    $(".checkAll").attr("checked", false)
                }
                b();
                a()
            }
        }
    });
    $(".btns .cart").click(function () {
        if ($(".categ p").hasClass("on")) {
            var c = parseInt($(".num span").text());
            var d = parseInt($(".goCart span").text());
            $(".goCart span").text(c + d)
        }
    });
    $(".del").click(function () {
        if ($(this).parent().parent().hasClass("th")) {
            $(".mask").show();
            $(".tipDel").show();
            index = $(this).parents(".th").index() - 1;
            $(".cer").click(function () {
                $(".mask").hide();
                $(".tipDel").hide();
                $(".th").eq(index).remove();
                $(".cer").off("click");
                if ($(".th").length == 0) {
                    $(".table .goOn").show()
                }
            })
        } else {
            if ($(".th input[type='checkbox']:checked").length == 0) {
                $(".mask").show();
                $(".pleaseC").show()
            } else {
                $(".mask").show();
                $(".tipDel").show();
                $(".cer").click(function () {
                    $(".th input[type='checkbox']:checked").each(function (c) {
                        index = $(this).parents(".th").index() - 1;
                        $(".th").eq(index).remove();
                        if ($(".th").length == 0) {
                            $(".table .goOn").show()
                        }
                    });
                    $(".mask").hide();
                    $(".tipDel").hide();
                    b();
                    a()
                })
            }
        }
    });
    $(".cancel").click(function () {
        $(".mask").hide();
        $(".tipDel").hide()
    })
});


function addProduct(pid) {
    let number = $("#number").text();
    $.ajax({
        type: "post",
        url: "/zym/cart/addCart",
        dataType: "json",
        data: {
            "pid": pid,
            "number": number
        },
        success: function (data) {
            if (data.status == 'T') {
                layer.alert(data.msg, {icon: 6});
            }
            if (data.status == 'F') {
                layer.alert(data.msg, {icon: 5});
            }
        },
        error: function (errorStatus) {
            layer.alert("系统出错!" + errorStatus.status, {icon: 5});
        }
    })
}

function delCartByCid(cid, currPage) {
    layer.confirm('您确认要删除这条购物车吗？', {
        btn: ['确认', '取消'], title: '提示', icon: 7
    }, function () {
        $.ajax({
            url: "/zym/cart/delCart/" + cid,
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.status == 'T') {
                    layer.alert(data.msg, {icon: 6}, function () {
                        location.reload();
                    })
                }
                if (data.status == 'F') {
                    layer.alert(data.msg, {icon: 5}, function () {
                        showCartList(currPage);
                    })
                }
            },
            error: function (errorStatus) {
                layer.alert('系统异常' + errorStatus.status, {icon: 5});
            }
        })
    }, function () {
        layer.msg('已取消..');
        return false;
    });
    return false;//重点二！！！！  阻止提交行为！！
}

function addNumber(cid, currPage, realPrice) {
    let check_cid = document.getElementsByClassName(cid)[0];
    if (!check_cid.checked) {
        layer.alert('请先选中!', {icon: 6});
        return;
    }
    let number = $("#" + cid).text();
    if (number >= 5) {
        layer.alert('限购5件', {icon: 6})
    } else {
        $.ajax({
            type: "get",
            url: "/zym/cart/addNumber/" + cid,
            dataType: "json",
            success: function (data) {
                /*showCartList(currPage);*/
                $("#" + cid).text(data.num);
                $("#" + cid + "r").text(data.realPrice);

                checkPrice(realPrice,cid);
            },
            error: function (errorStatus) {
                layer.alert("系统出错!" + errorStatus.status, {icon: 5});
            }
        })
    }
}

function downNumber(cid, currPage, realPrice) {
    let check_cid = document.getElementsByClassName(cid)[0];
    if (!check_cid.checked) {
        layer.alert('请先选中!', {icon: 6});
        return;
    }
    let number = $("#" + cid).text();
    if (number <= 1) {
        return false;
    } else {
        $.ajax({
            type: "get",
            url: "/zym/cart/downNumber/" + cid,
            dataType: "json",
            success: function (data) {
                /*showCartList(currPage);*/
                $("#" + cid).text(data.num);
                $("#" + cid + "r").text(data.realPrice);
                checkPrice(realPrice,cid);
            },
            error: function (errorStatus) {
                layer.alert("系统出错!" + errorStatus.status, {icon: 5});
            }
        })
    }
}

window.onload = function () {
    let checks = document.getElementById("checks");
    let check = document.getElementsByName("check");
    checks.onclick = function () {
        for (let i = 0; i < check.length; i++) {
            check[i].checked = this.checked;
        }
    }
    for (let j = 0; j < check.length; j++) {
        check[j].onclick = function () {
            let flag = true;
            for (let i = 0; i < check.length; i++) {
                if (!check[i].checked) {
                    flag = false;
                    break;
                }
            }
            checks.checked = flag;
        }
    }
}

function checkPrice(realPrice, cid, price,status) {
    let check = document.getElementsByName("check");
    let totalPrice = document.getElementById("totalPrice");
    let check_cid = document.getElementsByClassName(cid)[0];

    if (check_cid.checked) {
        totalPrice.innerText = parseFloat(totalPrice.innerText) + realPrice;
    } else {
        totalPrice.innerText = parseFloat(totalPrice.innerText) - realPrice;
    }
}

function accounts() {
    let check = document.getElementsByName("check");
    let checkCid ="";
    let flag = false;
    for (let i = 0; i < check.length; i++) {
        if (check[i].checked) {
            flag = true;
            checkCid+=check[i].className+',';
        }
    }
    checkCid=checkCid.substring(0,checkCid.length);
    if (!flag) {
        layer.alert("你还没有选择商品!", {icon: 6, title: '提示'});
        return false;
    }
    let totalPrice = $("#totalPrice").text();

    $.ajax({
        url: "/zym/order/createOrder",
        type: "get",
        dataType: "text",
        data: {"checkCid":checkCid,"totalPrice":totalPrice},
        success: function (data) {
            if(data=="T") {
                layer.alert('订单创建成功!', {icon: 6}, function () {
                    location.href = "http://localhost:8080/zym/order/order.html";
                })
            }
            if(data=="F") {
                layer.alert('订单数据加载异常!', {icon: 6});
            }
        },
        error: function (xhr) {
            layer.alert('系统异常!' + xhr.status, {icon: 5});
        }
    })
}

function goBuy(pid){
    $.ajax({
        type: "post",
        url: "/zym/order/createSingleOrder",
        dataType: "text",
        data: {
           "pid":pid
        },
        success:function (data){
            if(data.status=='T'){
                location.href="http://localhost:8080/zym/order/order.html";
                return
            }
            if(data.status='F'){
                layer.alert('购买数据异常!',{icon:6});
            }
        },
        error:function (xhr){
            layer.alert('系统异常!' + xhr.status, {icon: 5});
        }
    })
}

