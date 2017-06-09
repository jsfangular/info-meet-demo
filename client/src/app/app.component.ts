import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { UserProfile, UserProfileService } from './user-profile';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
class AppComponent implements OnInit {

  title = 'Demo';
  userProfile: Observable<UserProfile>;

  constructor(private userProfileService: UserProfileService) {
    console.log('AppComponent construct');
  }

  ngOnInit(): void {
    console.log('AppComponent init');
    this.userProfile = this.userProfileService.getUserProfile();
  }
}

export { AppComponent };