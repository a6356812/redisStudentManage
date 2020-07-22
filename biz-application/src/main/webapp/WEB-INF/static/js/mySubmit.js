function toUpdateUser(e) {
    var realId= $(e).attr('id');
  var form=document.getElementById(realId.toString());
  basePath=form.action;
  form.action=basePath+"/student?method=toUpdateStudentView";
  form.submit();
}
function deleteStudent(e) {
    var realId= $(e).attr('id');
    var form=document.getElementById(realId.toString());
    basePath=form.action;
    form.action=basePath+"/student?method=removeStudent";
    form.submit();
}
function init(){
    var form=document.getElementById("init");
    basePath=form.action;
    form.action=basePath+"/student?method=toUserForm&page=1";
    form.submit();
}