import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';
import { UserProfile } from './user-profile.i';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush
})
class UserProfileComponent implements OnInit {

    @Input() profile: UserProfile;
    @Output() onLogOut: EventEmitter<any> = new EventEmitter<any>();

    constructor() {
        console.log('UserProfileComponent construct');
    }

    ngOnInit(): void {
        console.log('UserProfileComponent init');
    }

    logOut(): void {
        console.log('UserProfileComponent logOut');
        this.onLogOut.emit();
    }
}

export { UserProfileComponent };
