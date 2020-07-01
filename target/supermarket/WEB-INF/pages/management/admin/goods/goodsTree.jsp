<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/4/20
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head th:include="include :: header"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel-body">
                <div id="jsTree"></div>
            </div>
            <div class="form-group ">
                <div class="col-sm-12 col-sm-offset-12">
                    <button type="submit" onclick="loadUser()" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div th:include="include :: footer"></div>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/jsTree/jstree.js"></script>
</js>
<script type="text/javascript">
    $(document).ready(function() {
        getTreeData()
    });
    function getTreeData() {
        $.ajax({
            type : "GET",
            url : "/offineGoods/tree",
            success : function(tree) {
                loadTree(tree);
            }
        });
    }
    function loadTree(tree) {
        $('#jsTree').jstree({
            'core' : {
                'data' : tree
            },
            "plugins" : [ "checkbox" ]
        });
        $('#jsTree').jstree().open_all();
    }
    function loadUser(){
        var userNames,userIds;
        var ref = $('#jsTree').jstree(true); // 获得整个树
        userIds = ref.get_bottom_selected();
        users = ref.get_bottom_checked('true');
        var txt="";
        for(var user in users){
            if(users[user].state.mType=="user"){
                txt=txt+users[user].text+",";
            }
        }
        parent.loadUser(userIds,txt);
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
    }
</script>
</body>
</html>
