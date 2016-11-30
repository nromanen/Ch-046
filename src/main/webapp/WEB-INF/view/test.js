/**
 * Created by Admin on 30.11.16.
 */

$( document ).ready(function(){

    console.log('work');

    var $cat = $("#group"),
        $subcat = $("#subject");

    $cat.on("change",function(){
        var _rel = $(this).val();
        console.log(_rel);
        $subcat.find("option").attr("style","");
        $subcat.val("");
        if(!_rel) return $subcat.prop("disabled",true);
        $subcat.find("[rel="+_rel+"]").show();

        $subcat.prop("disabled",false);
    });

});