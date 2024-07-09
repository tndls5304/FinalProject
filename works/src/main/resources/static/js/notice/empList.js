const content = document.querySelector("#content");

$.ajax({
    url: "/board/notice",
    method: "get",
    success: (data) => {
        let str = "";

        for (let i = 0; i < data.length; i++) {
            str += `
                <div class="detail" data-noticeNo="${data[i].noticeNo}">
                    <div>${data[i].noticeNo}</div>
                    <div>${data[i].title}</div>
                    <div>${data[i].viewCount}</div>
                    <div>${data[i].crtnDate}</div>
                </div>
            `;
        }
        
        content.innerHTML = str;

        const details = document.querySelectorAll(".detail");
        details.forEach(detail => {
            detail.addEventListener("click", function(event) {
                const noticeNo = event.currentTarget.getAttribute('data-noticeNo');
                window.location.href = "/board/notice/detail?noticeNo=" + noticeNo;
            });
        });
    },
    error: () => {
        alert("공지사항 조회 실패하였습니다");
    }
});
