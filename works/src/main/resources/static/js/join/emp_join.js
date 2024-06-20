//-------------------------------------------수인
document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('id').addEventListener('onkeyup', function() {
      hideErrorMessage('id-error');
  });
  document.getElementById('password').addEventListener('onkeyup', function() {
      hideErrorMessage('password-error');
  });
  document.getElementById('password-check').addEventListener('onkeyup', function() {
      hideErrorMessage('passwordCheck-error');
  });
  document.getElementById('name').addEventListener('onkeyup', function() {
      hideErrorMessage('name-error');
  });
  document.getElementById('phone').addEventListener('onkeyup', function() {
      hideErrorMessage('phone-error');
  });
  document.getElementById('crn').addEventListener('onkeyup', function() {
      hideErrorMessage('crn-error');
  });
  document.getElementById('file-upload').addEventListener('onkeyup', function() {
      hideErrorMessage('file-error');
  });
});

function validateForm() {
  // Clear previous error messages
  clearErrors();

  var id = document.getElementById('id').value;
  var password = document.getElementById('password').value;
  var passwordCheck= document.getElementById('password-check').value;
  var name = document.getElementById('name').value;
  var phone = document.getElementById('phone').value;
  var crn = document.getElementById('crn').value;
  var fileUpload = document.getElementById('file-upload').value;

  var isValid = true;

  if (id.length < 4 || id.length > 10) {
      document.getElementById('id-error').innerText = '아이디는 4~10자여야 합니다.';
      document.getElementById('id-error').style.display = 'block';
      isValid = false;
  }
  if (password.length < 4 || password.length > 10) {
      document.getElementById('password-error').innerText = '비밀번호는 4~10자여야 합니다.';
      document.getElementById('password-error').style.display = 'block';
      isValid = false;
  }
  if (passwordCheck.length < 4 || passwordCheck.length > 10) {
      document.getElementById('passwordCheck-error').innerText = '비밀번호는 4~10자여야 합니다.';
      document.getElementById('passwordCheck-error').style.display = 'block';
      isValid = false;
  }
  if (password!==passwordCheck) {
      document.getElementById('password-mismatch-error').innerText = '비밀번호 불일치! 동일한 비밀번호를 입력하세요';
      document.getElementById('password-mismatch-error').style.display = 'block';
      isValid = false;
  }
  if (name.length < 1 || name.length > 10) {
      document.getElementById('name-error').innerText = '이름은 1~10자여야 합니다.';
      document.getElementById('name-error').style.display = 'block';
      isValid = false;
  }
  if (phone.length < 8 || phone.length > 15) {
      document.getElementById('phone-error').innerText = '전화번호는 8~15자여야 합니다.';
      document.getElementById('phone-error').style.display = 'block';
      isValid = false;
  }
  if (crn.length !== 12) {
      document.getElementById('crn-error').innerText = '사업자 등록 번호는 12자여야 합니다.';
      document.getElementById('crn-error').style.display = 'block';
      isValid = false;
  }
  if (!fileUpload) {
      document.getElementById('file-error').innerText = '사장 대표 사진을 업로드해주세요.';
      document.getElementById('file-error').style.display = 'block';
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

function previewImage(event) {
  var reader = new FileReader();
  reader.onload = function() {
      var output = document.createElement('img');
      output.src = reader.result;
      output.style.maxWidth = '200px'; // Set a max width for the preview image
      document.getElementById('reviewPreviewImage').innerHTML = '';
      document.getElementById('reviewPreviewImage').appendChild(output);
  };
  reader.readAsDataURL(event.target.files[0]);
}
