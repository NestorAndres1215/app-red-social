import { Component, OnInit } from '@angular/core';
import { LayoutUserComponent } from "../layout-user/layout-user.component";

@Component({
  selector: 'app-sidebar-user',
  templateUrl: './sidebar-user.component.html',
  styleUrls: ['./sidebar-user.component.css'],
  imports: [LayoutUserComponent]
})
export class SidebarUserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
