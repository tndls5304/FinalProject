document.addEventListener('DOMContentLoaded', function() {
              const calendarEl = document.getElementById('calendar');
              const calendar = new FullCalendar.Calendar(calendarEl, {
                        locale : 'ko',                                                               //한국어 설정
                        initialView: 'dayGridMonth',
                        selectable: true,
                        //캘린더 등록하기: 달력 일자 드래그 설정가능
                        select:function( selectionInfo) {
                                //----------곧 지워야하는 코드 --------
                                popStatus = 'create';
                                //------------------------------
                              const insertModal = document.getElementById("insertModal");
                              const startDate =document.querySelector("input[id=startDate]")
                              const endDate =document.querySelector("input[id=endDate]")
                               //TODO 3초뒤에 모달창 나오게 하는게 이게 맞는지 확인해보기 날짜가 이상해
                              insertModal.style.display="block";
                              startDate.value=selectionInfo.startStr;
                              endDate.value=selectionInfo.endStr;
                              },

                        eventClick:  function(info) {
                                      //----------곧 지워야하는 코드 --------
                                        popStatus = 'modify';
                                      //------------------------------
                                           //일단 모달창안에 참여자들 비워주기
                                           const test=document.querySelector('#partnerDetail').innerHTML="";
                                           console.log(test);
                                         //일단 전체 캘린더 목록을 쫙 뿌려줬기 때문에
                                         //상세정보는 따로 서버에 요청할 필요 없음 api에서 제공하는걸로 사용하면 됨
                                         const titleBox=document.querySelector('#titleDetail');
                                         titleBox.value=info.event.title;

                                         const contentBox=document.querySelector('#contentDetail');
                                         contentBox.value=info.event.extendedProps.content;

                                         const placeNameBox=document.querySelector('#placeNameDetail');
                                         placeNameBox.value=info.event.extendedProps.placeName;

                                         const latitudeBox=document.querySelector('#latitudeDetail');
                                         latitudeBox.value=info.event.extendedProps.latitude;

                                         const longitudeBox=document.querySelector('#longitudeDetail');
                                         longitudeBox.value=info.event.extendedProps.longitude;


                                         //파트너가져와야됨
                                         const insertDateBox=document.querySelector('#insertDate');
                                         insertDateBox.innerText=info.event.extendedProps.insertDate;

                                         const updateDateBox=document.querySelector('#updateDate');
                                         updateDateBox.innerText=info.event.extendedProps.updateDate;

                                         // 시작날짜 종료날짜는 서버에서 2024-07-10 20:39:26 이런식으로 보내주니까 날짜만 뽑아내는 작업하기
                                         const startBox = document.querySelector('#startDetail');
                                         startBox.value = info.event.start.toISOString().slice(0, 10); // yyyy-mm-dd 형식으로 변환

                                         const endBox = document.querySelector('#endDetail');
                                         endBox.value = info.event.end.toISOString().slice(0, 10);      // yyyy-mm-dd 형식으로 변환

                                         const calendarNoBox = document.querySelector('#detailNo');
                                          calendarNoBox.innerText=info.event.extendedProps.no;

                                         //클릭해서 상세정보 나오기전에 공개여부가 참여자가 있으면 사람들 목록을 ajax로 가져와야함
                                        const openRangeNo=info.event.extendedProps.openRangeNo;
                                        const calendarNo=info.event.extendedProps.no;

                                        //공개범위가 2라면(참여자 누구인지 디비에 조회해와야함 )
                                        if(openRangeNo==="2"){
                                              $.ajax({
                                                        url:'/admin/calendar/partner',
                                                        method:'GET',
                                                        data:{no:calendarNo},
                                                            success:function(partnerList){
                                                            for(let i=0;i<partnerList.length; i++){
                                                                             const partner=partnerList[i];
                                                                  //참여자들을 담을 새로운 디브 생성
                                                                            const empDiv=document.createElement('div');
                                                                            empDiv.setAttribute('class','empDiv');
                                                                            //사원번호가 담기는 span태그 생성
                                                                            const span1=document.createElement('span');
                                                                            span1.innerText=partner.empNo;
                                                            		        //사원 이름 담아주고
                                                                            const span2=document.createElement('span');
                                                                            span2.innerText=partner.empName;
                                                            		        //닫기 버튼도 넣어주기
                                                                            const span3=document.createElement('span');
                                                                            span3.innerText='x';
                                                                            span3.setAttribute('class','removeEmp');
                                                                            span3.setAttribute('onclick','removeEmp(this)');
                                                                            //
                                                                            empDiv.appendChild(span1);
                                                                            empDiv.appendChild(span2);
                                                                            empDiv.appendChild(span3);
                                                                            partnerDetail.appendChild(empDiv);
                                                                          }
                                                             },
                                                            error:function(errorMsg){
                                                            alert(errorMsg.responseText);
                                                            }
                                                      })//ajax
                                        }

                                        const openRangeBox=document.querySelector('#openRangeDetail');
                                        openRangeBox.value=openRangeNo;

                                         const myDetailModal=document.getElementById("myDetailModal");
                                         myDetailModal.style.display="block";

                       },   //이벤트클릭에 동작하는거 끝!

                        editable: true,                                          //수정가능
                        eventChange: function(obj) {                             // 이벤트가 수정되면 발생하는 이벤트
                                    console.log(obj);
                          },
                        eventRemove: function(obj){                              // 이벤트가 삭제되면 발생하는 이벤트
                                     console.log(obj);
                          },
                        timeZone: 'UTC',
                        customButtons:{
                                 myCustomButton:{
                                     text:"목록보기",
                                     click:function(){
                                         alert('후순위니까 클릭하지마로');
                                      }
                                 }
                        },
                         //헤더 툴바
                         headerToolbar:{
                         left: 'prev,next today,myCustomButton,mySaveButton'
                         }
              }); //calendar기본설정



             //화면 보여주기 시작
             calendar.render();
             //내가 원하는작업 캘린더리스트 쭉 다 조회해오자
             $.ajax({
                      url:"/admin/calendar/all",
                       success:function(list){
                          if (list.length > 0) {
                            for(let i = 0; i < list.length;i++){
                                 const calendarVo=list[i];
                                 calendar.addEvent({
                                    title:calendarVo.title,
                                    start:calendarVo.startDate,
                                    end:calendarVo.endDate,

                                    extendedProps: {
                                                insertDate: calendarVo.insertDate,
                                                updateDate: calendarVo.updateDate,
                                                no: calendarVo.no,
                                                content: calendarVo.content,
                                                openRangeNo: calendarVo.openRangeNo,
                                                placeName: calendarVo.placeName, // 장소
                                                latitude: calendarVo.latitude, // 위도
                                                longitude: calendarVo.longitude // 경도
                                                    }
                                 });
                            }
                         }
                      }//ajax성공했을때
             })//ajax

})//DOMContentLoaded

