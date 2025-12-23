console.log("boardModify.js in");

// modBtn 버튼을 클릭하면 title, content 만 readOnly 풀기
document.getElementById('modBtn').addEventListener('click', ()=>{
    document.getElementById('t').readOnly = false;
    document.getElementById('c').readOnly = false;

    // Form 태그의 submit 역할을 하는 버튼 생성
    // <button type="button" class="btn btn-success text-dark" id="regBtn">update</button>
    let regBtn = document.createElement('button');
    regBtn.setAttribute("type", "submit");
    regBtn.setAttribute("id", "regBtn");
    regBtn.classList.add("btn", "btn-success", "text-dark");
    regBtn.innerText = "update";

    // Form 태그의 마지막 요소로 추가
    document.getElementById('modForm').appendChild(regBtn);

    // modBtn, delBtn 삭제
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();
    document.getElementById('listBtn').remove();
})

// list 버튼 클릭하면 /board/list로 이동
// document.getElementById('listBtn').addEventListener('click', ()=>{
//     window.location.href = "/board/list";
// })
