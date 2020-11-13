<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <script type="text/javascript" src="/js/jquery-2.1.4.js"></script>
</head>
<body>
<table class="layui-hide" id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['laypage', 'layer', 'table'], function(){
        var laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格

        //向世界问个好
        layer.msg('Hello World');

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 420
            ,url: '/tabledata' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
                ,{field: 'userName', title: '用户名', width:80}
                ,{field: 'experience', title: '积分', width: 90, sort: true, totalRow: true}
                ,{field: 'sex', title: '性别', width:80, sort: true}
                ,{field: 'score', title: '评分', width: 80, sort: true, totalRow: true}
                ,{field: 'city', title: '城市', width:150}
                ,{field: 'sign', title: '签名', width: 200}
                ,{field: 'classify', title: '职业', width: 100}
                ,{field: 'wealth', title: '财富', width: 135, sort: true, totalRow: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            var editList = [];
            $.each(data,function(name,value) {//循环遍历json数据
                editList.push(value);//将json数据中的value放入数组中（下面的子窗口显示的时候要用到）
            });
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
                layer.open({
                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    type: 1,
                    title: "修改信息",
                    area: ['420px', '330px'],
                    content: $("#popUpdateTest"),//引用的弹出层的页面层的方式加载修改界面表单
                    success: function(layero, index){
                        var inputList = $("#popUpdateTest").find("input");//获取到子窗口的所有的input标签
                        for (var i = 0; i < inputList.length; i++ ) {
                            $(inputList[i]).val(editList[i]); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        }
                    }
                });
            }
        });

        //分页
        laypage.render({
            elem: 'pageDemo' //分页容器的id
            ,count: 100 //总页数
            ,skin: '#1E9FFF' //自定义选中色值
            //,skip: true //开启跳页
            ,jump: function(obj, first){
                if(!first){
                    layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                }
            }
        });
    });
</script>
</body>
</html>
