let defaultOption = "<option value='0'>----请选择---</option>";


$(function () {
    showProvinceList();
    $("#city_list").append(defaultOption);
    $("#area_list").append(defaultOption);

    let addressTable=$("#addressTable");
    $.ajax({
        url:"/zym/usersMsg/addressShow",
        type:"get",
        dataType:"JSON",
        async:false,
        success:function (data){
            for (let i = 0; i <data.length ; i++) {
                let addressMsg='<tr class="addressColor"><td>#{name}</td>\n' +
                    '                    <td>#{phone}</td>\n' +
                    '                    <td>#{areaMsg}</td>\n' +
                    '                    <td>#{address}</td>'+
                    '                    <td><a class="btn_default" style="cursor: pointer;" onclick="setDefault('+data[i].aid+')">设为默认</a>&nbsp;<a href="" class="btn-primary">修改</a>&nbsp;<a style="cursor: pointer;" class="btn-danger" onclick="deleteAddress(#{aid})">删除</a></td></tr>'
                addressMsg=addressMsg.replace(/#{name}/g,data[i].name);
                addressMsg=addressMsg.replace(/#{phone}/g,data[i].phone);
                addressMsg=addressMsg.replace("#{areaMsg}",data[i].provinceName+' '+data[i].cityName+' '+data[i].areaName);
                addressMsg=addressMsg.replace("#{address}",data[i].address);
                addressMsg=addressMsg.replace("#{aid}",data[i].aid);
                addressTable.append(addressMsg);
                $(".btn_default:eq(0)").hide();
            }
        },
        error:function (errorStatus){
            layer.alert('系统异常'+errorStatus.status,{icon:5});
        }
    });
    $(".addressColor:eq(0)").attr('class','addressDefaultColor');
})
//当省份文本域发生改变，则改变市区下拉的值
$("#province_list").change(function () {
    //首先获取父code
    let parent = $("#province_list").val();
    $("#city_list").empty();  //插入之前清空select里面的option
    $("#area_list").empty();

    $("#city_list").append(defaultOption);
    $("#area_list").append(defaultOption);

    if (parent == 0) {
        return;
    }

    $.ajax({
        url: "/zym/usersMsg/district",
        type: "get",
        dataType: "JSON",
        data: "parent=" + parent,
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                let opt = "<option value='" + data[i].code + "'>" + data[i].name + "</option>";
                $("#city_list").append(opt);
            }
        },
        error: function (errorStatus) {
            layer.alert('系统异常' + errorStatus.status, {icon: 5});
        }
    })
})

//获取区域的下拉列表
$("#city_list").change(function () {
    //首先获取父code
    let parent = $("#city_list").val();

    $("#area_list").empty();

    $("#area_list").append(defaultOption);

    if (parent == 0) {
        return;
    }

    $.ajax({
        url: "/zym/usersMsg/district",
        type: "get",
        dataType: "JSON",
        data: "parent=" + parent,
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                let opt = "<option value='" + data[i].code + "'>" + data[i].name + "</option>";
                $("#area_list").append(opt);
            }
        },
        error: function (errorStatus) {
            layer.alert('系统异常' + errorStatus.status, {icon: 5});
        }
    })
})

//省份的下拉数据展示
function showProvinceList() {
    $.ajax({
        url: "/zym/usersMsg/district",
        type: "post",
        dataType: "JSON",
        data: "parent=86",
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                let opt = "<option value='" + data[i].code + "'>" + data[i].name + "</option>";
                $("#province_list").append(opt);
            }
        },
        error: function (errorStatus) {
            layer.alert('系统异常' + errorStatus.status, {icon: 5});
        }
    })
}

function addNewAddress() {
    let addressMsg = $("#form_add_new_address").serialize();
    $.ajax({
        url: "/zym/usersMsg/addNewAddress",
        type: "post",
        dataType: "json",
        data: addressMsg,
        success: function (data) {
            if (data.status == 'T') {
                layer.alert(data.msg, {icon: 1}, function () {
                    location.reload();
                });
            }
            if (data.status == 'F') {
                layer.alert(data.msg, {icon: 5}, function () {
                    location.reload();
                });
            }
        },
        error: function (errorStatus) {
            layer.alert('系统异常' + errorStatus.status, {icon: 5});
        }
    })
}

function setDefault(aid){
    $.ajax({
        url:"/zym/usersMsg/"+aid+"/setDefaultAddress",
        type:"get",
        dataType:"json",
        success:function (data) {
            if (data.status == 'T') {
                layer.alert(data.msg, {icon: 6}, function () {
                    location.reload();
                })
            }
            if (data.status == 'F') {
                layer.alert(data.msg, {icon: 5}, function () {
                    location.reload();
                })
            }
        },
        error: function (errorStatus) {
            layer.alert('系统异常' + errorStatus.status, {icon: 5});
        }
    })
}

/*function deleteAddress(aid){
      layer.alert('确认删除吗',{icon:7},function (){
          $.ajax({
              url: "/zym/usersMsg/deleteAddress/" + aid,
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
                          location.reload();
                      })
                  }
              },
              error: function (errorStatus) {
                  layer.alert('系统异常' + errorStatus.status, {icon: 5});
              }
          })
      })

}*/


function deleteAddress(aid){
    layer.confirm('您确认要删除吗？', {
        btn: ['确认','取消'],title:'提示',icon:7
    }, function(){
        $.ajax({
            url: "/zym/usersMsg/deleteAddress/" + aid,
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
                        location.reload();
                    })
                }
            },
            error: function (errorStatus) {
                layer.alert('系统异常' + errorStatus.status, {icon: 5});
            }
        })
    }, function(){
        layer.msg('已取消..');
        return false;
    });
    return false;//重点二！！！！  阻止提交行为！！
}
