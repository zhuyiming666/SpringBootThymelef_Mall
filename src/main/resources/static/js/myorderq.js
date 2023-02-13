$(function (){
    showMyOrder(1);
})

function showMyOrder(status){
    $("#myorderDiv").html("");
    $.ajax({
        url:"/zym/order/showMyOrder",
        type:"get",
        dataType:"json",
        data:{"status":status},
        success:function (map){
            for(let i in map){
                let orderVos=map[i];
                let div1='<div id="map'+i+'" class="dkuang">\n' +
                    '                <p class="one">已收货</p>\n' +
                    '                <div class="word clearfix">\n' +
                    '                    <ul class="fl clearfix">\n' +
                    '                        <li>'+orderVos[0].orderTime+'</li>\n' +
                    '                        <li>'+orderVos[0].recvName+'</li>\n' +
                    '                        <li>在线支付</li>\n' +
                    '                    </ul>\n' +
                    '                    <p class="fr">订单金额：<span>'+orderVos[0].totalPrice+'</span>元</p></div>';
                $("#myorderDiv").append(div1);
                for (let x=0;x<orderVos.length;x++){
                     let div2='<div class="shohou clearfix">\n' +
                         '                    <a href="#" class="fl"><img id="myOrderImge" src="/zym/shooping_Imgs/'+orderVos[x].image+'"/></a>\n' +
                         '                    <p class="fl"><a href="#">'+orderVos[0].title+'</a><a href="#">'+orderVos[0].price+'</a></p>\n' +
                         '                    <p class="fr"><a href="myprod.html">待评价</a><a th:href="@{/order/orderxq.html}">订单详情</a></p>\n' +
                         '                </div>';
                     $("#map"+i).append(div2);
                }
            }
        },
        error:function (xhr){
            layer.alert("系统异常"+xhr.status);
        }
    })
}