const openButton = document.querySelector('.open');
const container = document.querySelector('.container');
const closeButton = document.querySelector('.close');

openButton.addEventListener('click', () => {
    container.style.display = "flex";
    openButton.style.display = "none";
});

closeButton.addEventListener('click', () => {
  container.style.display = "none";
  openButton.style.display = "";
  // openButton.style.display = "flex";
});

// 모달 밖 클릭했을 때 모달창 꺼짐
container.addEventListener('click', (event) => {
  if (event.target === container) {
    container.style.display = 'none';
    openButton.style.display = 'block';
  }
});


// 마우스 스크롤 모달에서 작동하게 설정하기
// 모달 on
body.style.overflow = 'hidden';

// 모달 off
body.style.overflow = 'auto';


// 모달 크기 조절
// function WindowModalResize()
// {
//   window.dialogWidth=$(document).width() + 'px';
//   window.dialogHeight=$(document).height() + 20 + 'px';
// }

windowSize = function(x,y){
  window.dialogWidth = x + "px";
  window.dialogHeight = y + "px";
}