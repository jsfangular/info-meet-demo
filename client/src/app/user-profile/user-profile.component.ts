import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';
import { UserProfile } from './user-profile.i';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
class UserProfileComponent implements OnInit {

    @Input() profile: UserProfile;
    @Output() onLogIn: EventEmitter<any> = new EventEmitter<any>();
    @Output() onLogOut: EventEmitter<any> = new EventEmitter<any>();

    constructor() {
        console.log('UserProfileComponent construct');
    }

    ngOnInit(): void {
        console.log('UserProfileComponent init');
    }

    logIn(): void {
        console.log('UserProfileComponent logIn');
        this.onLogIn.emit();
    }

    logOut(): void {
        console.log('UserProfileComponent logOut');
        this.onLogOut.emit();
    }
}

export { UserProfileComponent };
