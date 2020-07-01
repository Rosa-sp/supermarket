
$(function() {

 KindEditor.ready(function(K){
  window.editor = K.create("#context", {
   filterMode : true,
   items:springstage.kindEditorItems,
   uploadJson : '/management/image/editorUpload'
  });
 });

});

function save(){
 window.editor.sync();
 $("#articleForm").submit();
}