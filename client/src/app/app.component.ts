import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/switchMap';

import { UserProfile, UserProfileService } from './user-profile';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
class AppComponent implements OnInit {

  userProfile: Observable<UserProfile>;

  constructor(private userProfileService: UserProfileService) {
    console.log('AppComponent construct');
  }

  ngOnInit(): void {
    console.log('AppComponent init');
    this.userProfile = this.userProfileService.getUserProfile();
  }

  logOut(): void {
    console.log('AppComponent logOut');
    let loadUserProfile: Observable<UserProfile> = this.userProfileService.getUserProfile();
    this.userProfile = this.userProfileService.logOutUserProfile().switchMap(() => loadUserProfile);
  }
}

export { AppComponent };