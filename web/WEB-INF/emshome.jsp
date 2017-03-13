<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
   <title>Employee Management System</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="../css/w3.css">
   <link rel="stylesheet" href="../css/bootstrap.min.css">
   <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
   <link rel="stylesheet" href="../css/jquery.dataTables.min.css">
   <%--<link href="https://fonts.googleapis.com/css?family=Pattaya|Prompt" rel="stylesheet">--%>
   <body>
      <div class="header-style" style="font-family:Prompt">ระบบจัดการพนักงาน</div>
      <nav class="w3-sidenav w3-black w3-card-2" style="width:150px">
         <a href="javascript:void(0)" class="tablink" onclick="openLink(event, 'HomePage')" style="font-family:Prompt">
         <img src="../images/home-interface.png" style="width: 20px;height: 20px">&nbspหน้าแรก</a>
         <a href="javascript:void(0)" class="tablink" onclick="openLink(event, 'WorkingHistory')" style="font-family:Prompt"><img src="../images/clock.png" style="width: 20px;height: 20px;">&nbspประวัติการทำงาน</a>
         <a href="javascript:void(0)" class="tablink" onclick="openLink(event, 'Salary')" style="font-family:Prompt"><img src="../images/payment-method.png" style="width: 20px;height: 20px">&nbspเงินเดือนพนักงาน</a>
         <a href="javascript:void(0)" class="tablink" onclick="openLink(event, 'Position')" style="font-family:Prompt"><img src="../images/users-group.png" style="width: 20px;height: 20px">&nbspตำแหน่งพนักงาน</a>
      </nav>
      <div style="margin-left:10%">
         <!-- HOMEPAGE -->
         <div id="HomePage" class="w3-container-body city w3-animate-left" style="font-family:Prompt;display: none;">
            <div class="col-lg-8" style="margin-top: 3%;width: 100%">
               <table class="display" id="empName">
                  <thead >
                     <tr>
                        <th>ชื่อพนักงาน</th>
                        <th>ตำแหน่ง</th>
                        <th>ประเภท</th>
                        <th>ค่าจ้าง</th>
                        <th></th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr>
                        <td>NATTHANON</td>
                        <td>Waitor</td>
                        <td>Full-Time</td>
                        <td>300</td>
                        <td><button class="buttonForEditEmp" data-toggle="modal" data-target="#myModal">แก้ไขข้อมูล</button></td>
                     </tr>
                     <tr>
                        <td>JOHN DOE</td>
                        <td>DUMMY</td>
                        <td>Full-Time</td>
                        <td>300</td>
                        <td><button class="buttonForEditEmp">แก้ไขข้อมูล</button></td>
                     </tr>
                     <tr>
                        <td>JOHN DOE</td>
                        <td>DUMMY</td>
                        <td>Full-Time</td>
                        <td>300</td>
                        <td><button class="buttonForEditEmp">แก้ไขข้อมูล</button></td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
               <!-- เนือหาของ Modal ทั้งหมด -->
               <div class="modal-content" style="font-family: Prompt">
                  <!-- ส่วนหัวของ Modal -->
                  <div class="modal-header">
                     <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                     <h4 class="modal-title">แก้ไขข้อมูลของ ....</h4>
                  </div>
                  <!-- ส่วนเนื้อหาของ Modal -->
                  <div class="modal-body">
                     <table>
                        <tr>
                           <td>ชื่อ : <input type="text"></td>
                           <td> หมายเลขบัตรประชาชน : <input type="text"></td>
                        </tr>
                        <tr>
                           <td>เพศ : <input type="radio">ชาย</input><br>
                              <input type="radio" name="">หญิง</input>
                           </td>
                           <td>เบอร์โทรศัพท์ : <input type="number" name=""></td>
                        </tr>
                        <tr>
                          <td>
                          ตำแหน่ง :<select>
                              <option value="Position1">Position1</option>
                              <option value="Position2">Position2</option>
                              <option value="Position3">Position3</option>
                              <option value="Position4">Position4</option>
                          </select> </td>
                          <td>ประเภท :<select>
                            <option value="Position1">Position1</option>
                              <option value="Position2">Position2</option>
                              <option value="Position3">Position3</option>
                              <option value="Position4">Position4</option>
                          </select></td>
                        </tr>

                     </table>
                  </div>
                  <div class="modal-footer">
                     <!-- ปุ่มกดปิด (Close) ตรงส่วนล่างของ Modal -->
                     <button type="button" class="buttonForEditEmp" >บันทึกการเปลี่ยนแปลง</button>
                     <button type="button" class="buttonForCancel" data-dismiss="modal">
                     ยกเลิก</button>
                  </div>
               </div>
            </div>
         </div>
         <!-- ENDOFHOMEPAGE -->
         <!-- WORKINGHISTORY -->
         <div id="WorkingHistory" class="w3-container-body city w3-animate-left" style="font-family:Prompt; display: none;">
            ไว้ใส่หน้าข้อมูลพักงาน
         </div>
         <!-- SALARY -->
         <div id="Salary" class="w3-container-body city w3-animate-left" style="font-family:Prompt;display: none;">
            ไว้ใส่ข้อมูลเงินเดือนพนักงาน
         </div>
         <!-- POSITION -->
         <div id="Position" class="w3-container-body city w3-animate-left" style="display:none;font-family:Prompt">
            ไว้ใส่ตำแหน่งพนักงาน
         </div>
      </div>
      <script>
         function openLink(evt, animName) {
           var i, x, tablinks;
           x = document.getElementsByClassName("city");
           for (i = 0; i < x.length; i++) {
              x[i].style.display = "none";
           }
           tablinks = document.getElementsByClassName("tablink");
           for (i = 0; i < x.length; i++) {
              tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
           }
           document.getElementById(animName).style.display = "block";
           evt.currentTarget.className += " w3-red";
         }
      </script>
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="../js/bootstrap.min.js"></script>
      <script src="../js/jquery.dataTables.min.js"></script>
      <script type="text/javascript">
         $(document).ready(function(){
             $('#empName').DataTable({
               
             });       
         });
      </script>
      <script type="text/javascript">
         function EditScreenButton(){
             display 
         
         }
      </script>
   </body>
</html>