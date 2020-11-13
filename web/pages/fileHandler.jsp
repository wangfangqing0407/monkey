<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body >
    <form action="/file/upload" method="post" enctype="multipart/form-data">
        <h1>文件上传</h1>
        文件：<input type="file" name="uploadFile"/><br/>
        文件：<input type="file" name="uploadFile"/><br/>
        文件：<input type="file" name="uploadFile"/><br/>
        <input type="submit" value="上传">
    </form>

    <h1>文件下载</h1>
    <a href="/file/download?filename=TIM截图20201104111538.jpg">下载</a>
    <a href="/file/download2?filename=TIM截图20201104111538.jpg">下载2</a>
</body>

<script type="text/javascript" src="/js/jquery-2.1.4.js"></script>
<script type="text/javascript">

</script>
</html>
