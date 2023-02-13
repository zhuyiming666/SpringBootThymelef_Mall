$(function () {
    $.ajax({
        type: "get",
        url: "/zym/usersMsg/addressShow",
        dataType: "json",
        success: function (data) {
            let addressList = $("#address_list");
            for (let i = 0; i < data.length; i++) {
                let options = '<option value="#{aid}">#{name}&nbsp;&nbsp;#{provinceName}&nbsp;&nbsp;#{cityName}&nbsp;&nbsp;#{areaName}&nbsp;&nbsp;#{address}&nbsp;&nbsp;#{phone}</option>';
                options=options.replace(/#{aid}/g,data[i].aid);
                options=options.replace(/#{name}/g,data[i].name);
                options=options.replace(/#{provinceName}/g,data[i].provinceName);
                options=options.replace(/#{cityName}/g,data[i].cityName);
                options=options.replace(/#{areaName}/g,data[i].areaName);
                options=options.replace(/#{address}/g,data[i].address);
                options=options.replace(/#{phone}/g,data[i].phone);
                addressList.append(options);
            }
        },
        error: function (xhr) {
            layer.alert("购物车地址列表加载异常!" + xhr.status);
        }
    })
})

$(function (){
    $.ajax({
        type: "get",
        url: "/zym/order/showOrderItem",
        dataType: "json",
        success:function (data){
            if(data.status=='T'){
                /*layer.alert('订单显示成功!',{icon:6});*/
                let orderItemsList=data.msg;
                let price=0;
                let oid_value;
                for (let i = 0; i < orderItemsList.length; i++) {
                     let ul='<ul class="clearfix">\n' +
                         '                    <li class="fl"><img id="orderItemImg" src="/zym/shooping_Imgs/'+orderItemsList[i].image+'"></li>\n' +
                         '                    <li class="fl"><p>'+orderItemsList[i].title+'</p>\n' +
                         '                        <p>颜色分类:暂无</p>\n' +
                         '                        <p>数量:'+orderItemsList[i].num+'</p></li>\n' +
                         '                    <li class="fr">￥'+orderItemsList[i].price+'</li>\n' +
                         '                </ul>';
                     $("#orderItemMsg").append(ul);
                     price+=orderItemsList[i].price;
                     oid_value=orderItemsList[i].oid;
                }
                $("#productPrice").text(price);
                $("#product_realPrice").text(price);
                $("#oid").val(oid_value);
            }
        },
        error:function (xhr){
            layer.alert("购物车地址列表加载异常!" + xhr.status);
        }
    })
})

$(function (){
    $("#address_list").change(function (){
        let aid=$("#address_list").val();
        let oid=$("#oid").val();

        $.ajax({
            type:"get",
            url:"/zym/order/updateOrderAddress",
            dataType:"json",
            data:{"oid":oid,"aid":aid},
            success:function (data){
                if(data.status=='T'){
                    layer.alert(data.msg,{icon:6})
                }
                if (data.status=='F'){
                    layer.alert(data.msg,{icon:5});
                }
            }
        })
    })
})

function ok(){
    let oid=$("#oid").val();
    location.href="http://localhost:8080/zym/order/ok.html?oid="+oid;
}

function cancel(){
    let oid=$("#oid").val();
    $.ajax({
        url:"/zym/order/cancel",
        type:"get",
        dataType:"json",
        data: {"oid":oid},
        success:function (data){
            if(data.status=='T'){
                layer.alert(data.mag,{icon:1},function (){
                    location.href="http://localhost:8080/zym/cart/cart.html";
                });
            }
            if(data.status=='F'){
                layer.alert(data.msg,{icon:1});
            }
        },
        error:function (xhr){
            layer.alert("取消订单异常!" + xhr.status);
        }
    })
}
