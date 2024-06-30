//-------------------------------------------수인
document.addEventListener('DOMContentLoaded', function() {

  document.getElementById('id').addEventListener('onkeyup', function() {
      hideErrorMessage('id-error');
  });
  document.getElementById('pwd').addEventListener('onkeyup', function() {
      hideErrorMessage('pwd-error');
  });
  document.getElementById('pwdCheck').addEventListener('onkeyup', function() {
      hideErrorMessage('pwdCheck-error');
  });
  document.getElementById('name').addEventListener('onkeyup', function() {
      hideErrorMessage('name-error');
  });
  document.getElementById('phone').addEventListener('onkeyup', function() {
      hideErrorMessage('phone-error');
  });

  document.getElementById('profile').addEventListener('onkeyup', function() {
      hideErrorMessage('profile-error');
  });
});

function validateForm() {
  // Clear previous error messages
  clearErrors();

  var id = document.getElementById('id').value;
  var pwd = document.getElementById('pwd').value;
  var pwdCheck= document.getElementById('pwdCheck').value;
  var name = document.getElementById('name').value;
  var phone = document.getElementById('phone').value;
  var profile = document.getElementById('profile').value;

  var isValid = true;

  if (id.length < 4 || id.length > 30) {
      document.getElementById('id-error').innerText = '아이디는 4~30자여야 합니다.';
      document.getElementById('id-error').style.display = 'block';
      isValid = false;
  }
  if (pwd.length < 4 || pwd.length > 100) {
      document.getElementById('pwd-error').innerText = '비밀번호는 4~100자여야 합니다.';
      document.getElementById('pwd-error').style.display = 'block';
      isValid = false;
  }
  if (pwdCheck.length < 4 || pwdCheck.length > 100) {
      document.getElementById('pwdCheck-error').innerText = '비밀번호는 4~100자여야 합니다.';
      document.getElementById('pwdCheck-error').style.display = 'block';
      isValid = false;
  }
  if (pwd!==pwdCheck) {
      document.getElementById('pwd-mismatch-error').innerText = '비밀번호 불일치! 동일한 비밀번호를 입력하세요';
      document.getElementById('pwd-mismatch-error').style.display = 'block';
      isValid = false;
  }
  if (name.length < 1 || name.length > 30) {
      document.getElementById('name-error').innerText = '이름은 1~30자여야 합니다.';
      document.getElementById('name-error').style.display = 'block';
      isValid = false;
  }
  if (phone.length !== 11) {
      document.getElementById('phone-error').innerText = '휴대폰번호는 11자여야 합니다.';
      document.getElementById('phone-error').style.display = 'block';
      isValid = false;
  }

  if (!profile) {
      document.getElementById('profile-error').innerText = '프로필 사진을 업로드해주세요.';
      document.getElementById('profile-error').style.display = 'block';
      isValid = false;
  }

  if (isValid) {
//제대로 입력될때만 제출되게 하기
      document.getElementById('signupForm').submit();
  }
}

function hideErrorMessage(id) {
  var element = document.getElementById(id);
  element.innerText = '';
  element.style.display = 'none';
}

function clearErrors() {
  var errorMessages = document.getElementsByClassName('error-message');
  for (var i = 0; i < errorMessages.length; i++) {
      errorMessages[i].innerText = '';
      errorMessages[i].style.display = 'none';
  }
}

//function previewImage(event) {
//  var reader = new FileReader();
//  reader.onload = function() {
//      var output = document.createElement('img');
//      output.src = reader.result;
//      output.style.maxWidth = '200px'; // Set a max width for the preview image
//      document.getElementById('reviewPreviewImage').innerHTML = '프로필 사진 업로드 완료♡';
//      document.getElementById('reviewPreviewImage').appendChild(output);
//  };
//  reader.readAsDataURL(event.target.files[0]);
//}


//아이디중복검사 누르면 ajax요청
window.onload = function () {
    const idDupTestBtn= document.querySelector("#idDupTestBtn");
    idDupTestBtn.addEventListener("click",function(){
        const idInputTag=document.querySelector("#id");
        const idValue=idInputTag.value;
        console.log(idValue);
        $.ajax({
                  url:"http://localhost:8080/emp/join_duplicateTest",
                  method:"GET",
                   data:{id:idValue},
                   success:function(resultMsg){
                          alert(resultMsg);
                   },
                   error:function(fail){
                   console.log("통신실패")
                   }

                })
        });
 };

