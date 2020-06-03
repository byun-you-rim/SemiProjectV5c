$('#pdregbtn').on('click', function () {
    if ($('#title').val() == '') {
        alert('제목을 입력하세요!!');
        $('#title').focus();

    } else if ($('#userid').val() == '') {
        alert('작성자를 입력하세요!!');
        $('#userid').focus();

    } else if ($('#contents').val() == '') {
        alert('본문을 입력하세요!!');
        $('#contents').focus();

    } else {
        $('#bdfrm').submit();
    }
}); // 새글등록

$('#newgal').on('click', function () {
    location.href = '/gallery/write.do';
}); // 새글쓰기

$('#lstgal').on('click', function () {
    location.href = '/gallery/list.do';
}); // 목록으로

$('#galnobtn').on('click', function () {
    location.href = '/gallery/list.do';
}); // 취소하기

$('#delgal').on('click', function () {
    var isDelete = confirm("정말 삭제할거니..? 두번은 묻지않아...");
    if(isDelete){
        var bno = $('#bno').val();
        location.href =
            '/gallery/delete.do?gno=';
    }


}); // 삭제하기

$('#updgal').on('click', function () {
    location.href =
        '/gallery/update.do?gno=';
}); // 수정하기

$('#replybtn').on('click',function () {
    if($('#reply').val()==""){
        alert('댓글을 작성하세요');
    } else {

        $('#replyfrm').attr('action','/reply/bdrpywrite');
        $('#replyfrm').submit();
    }
}); // 댓글쓰기

// 대댓글 쓰기
function addReply(refno) {
    $('#cmtModal').modal('show');
    $('#refno').val(refno);
    // 모달창을 띄웠을때
    // 해당 댓글 번호를 refno 폼 요소에 저장함
}
$('#cmtbtn').on('click',function () {
    if($('#comment').val()=="")
        alert('대댓글을 작성하세요!!');
    else
        $('#cmtfrm').attr('action','/reply/bdcmtwrite');
        $('#cmtfrm').submit();
})