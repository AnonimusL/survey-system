import { Component, OnInit } from '@angular/core';
import { SurveySubmenuService } from 'src/app/services/survey-submenu.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  submenus: { [key: string]: boolean } 

  constructor(private surveySubmenuService: SurveySubmenuService) { 
    this.submenus = surveySubmenuService.submenuSidebar;
  }

  ngOnInit(): void {
  }

  toggleSubmenu(menuKey: string): void {
    console.log(menuKey)
    let flag = this.surveySubmenuService.submenuSidebar[menuKey]
    // Reset all submenu states to false
    Object.keys(this.surveySubmenuService.submenuSidebar).forEach(key => this.surveySubmenuService.submenuSidebar[key] = false);
  

    this.surveySubmenuService.submenuSidebar[menuKey] = !flag
    this.submenus = this.surveySubmenuService.submenuSidebar
    
  }
}
