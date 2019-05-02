$(function () {
    initialize();
});

let user;

let initialize = async () => {
    $.get('http://localhost:8080/post/readByAccount/teomc')
        .done(response => {
            $('#postId').html(response.data.id);
            $('#title').html(response.data.title);
            $('#writer').html(response.data.name);
            $('#post_created').html(response.data.created);
            $('#content').html(response.data.content);
    })
        .fail(() => {
            console.log("초기화 실패");
    });

    $.get('http://localhost:8080/user/read/teomc')
        .done(response => {
            user = response.data;
            $('#account').html(response.data.account);
            $('#username').html(response.data.name);
            $('#user_email').html(response.data.email);
            $('#user_phone').html(response.data.phone);
        $('#user_created').html(response.data.created);
    });

    $.get('http://localhost:8080/post/readall')
        .done(response => {
            $('#postList').html("");

            response.data.map(value => {
                $('#postList').prepend(`<li onclick="postSelected(${value.id})">${value.title}</li>`);
            });
    })
};

async function postSelected(id) {
    console.log(id);
    await $.get('http://localhost:8080/post/read/' + id)
      .done(response => {
        $('#postId').html(response.data.id);
        $('#title').html(response.data.title);
        $('#writer').html(response.data.name);
        $('#post_created').html(response.data.created);
        $('#content').html(response.data.content);
    })
      .fail(() => {
        console.log("초기화 실패");
    });
}

let titleVal;
let contentVal;
let writerVal;
let createdVal;

function setWriteForm() {
    titleVal = $('#title').html();
    contentVal = $('#content').html();
    writerVal = $('#writer').html();
    createdVal = $('#post_created').html();

    $('#title').html(`<input id="titleForm">`);
    $('#content').html(`<textarea id="contentForm" rows="40" cols="80" ></textarea>`);
    $('#writer').html('');
    $('#post_created').html('');

    $('#editBtn').text('작성');
    $('#deleteBtn').text('취소');
}

async function editPost() {
    if($('#editBtn').text() === "작성") {
        let title = $('#titleForm').val();
        let content = $('#contentForm').val();

        if(!title || !content) {
            alert("제목 혹은 내용이 작성되지 않았습니다.");
            return;
        } else {
            content = content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
            let data = new Object();
            data.account = user.account;
            data.name = user.name;
            data.title = title;
            data.content = content;

            try {
                let response = await $.ajax
                ({
                    type: "POST",
                    url: 'http://localhost:8080/post/create',
                    contentType: "application/json",
                    data: JSON.stringify(data)
                });

                initialize();

                $('#editBtn').text('수정');
                $('#deleteBtn').text('삭제');

            } catch (e) {
                console.log(e.message);
            }
        }
    } else if($('#editBtn').text() === '수정'){
        titleVal = $('#title').html();
        contentVal = $('#content').html();
        writerVal = $('#writer').html();
        createdVal = $('#post_created').html();

        $('#title').html(`<input id="titleForm" value="${titleVal}">`);
        $('#content').html(`<textarea id="contentForm" rows="40" cols="80">${contentVal}</textarea>`);
        $('#writer').html('');
        $('#post_created').html('');

        $('#editBtn').text('완료');
        $('#deleteBtn').text('취소');
    } else if($('#editBtn').text() === '완료'){
        let title = $('#titleForm').val();
        let content = $('#contentForm').val();

        if(title === titleVal && content === contentVal) {
            alert('제목이나 내용 중 한 가지 이상은 수정해야 합니다.')
            return;
        } else {
            let data = new Object();
            data.id = $('#postId').html();
            data.account = user.account;
            data.name = user.name;
            data.title = title;
            data.content = content;

            try{
                console.log(data);
                let response = await $.ajax
                   ({
                        type: "PUT",
                        url: 'http://localhost:8080/post/update',
                        contentType: "application/json",
                        data: JSON.stringify(data)
                    });
                $('#title').html(response.data.title);
                $('#writer').html(response.data.name);
                $('#post_created').html(response.data.created);
                $('#content').html(response.data.content);

                $('#editBtn').text('수정');
                $('#deleteBtn').text('삭제');

            } catch (e) {
                console.log(e.message);
            }
        }
    }
}

async function deletePost() {
    if($('#deleteBtn').text() === "취소") {
        $('#title').html(titleVal);
        $('#writer').html(writerVal);
        $('#post_created').html(createdVal);
        $('#content').html(contentVal);

        $('#editBtn').text('수정');
        $('#deleteBtn').text('삭제');
    } else {
        if(confirm('정말 삭제하시겠습니까?')) {
            try {
                let response = await $.ajax
                ({
                    type: "delete",
                    url: 'http://localhost:8080/post/delete/' + $('#postId').html()
                });

                initialize();
            } catch (e) {
                console.log(e.message);
            }


        }
    }
}