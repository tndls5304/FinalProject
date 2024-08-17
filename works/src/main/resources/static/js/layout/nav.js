const btn = document.querySelector("#modalBtn");
btn.addEventListener('click' , ()=>{
    const modal = document.querySelector("#modal");
    if(modal.style.display === "block"){
        modal.style.display = "none"
    }else{
        modal.style.display="block";
    }
})