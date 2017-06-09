import { Injectable } from '@angular/core';
import { Http, Response, RequestOptionsArgs } from '@angular/http'

import { UserProfile } from './user-profile.i';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';

@Injectable()
class UserProfileService {

    private readonly HOST_PATH: string = 'http://localhost:8080';
    private readonly BASE_URL: string = '/server/restadapter';
    private readonly URL_SESSION: string = '/user-profile';
    private readonly URL_LOGOUT: string = this.URL_SESSION + '/logout';

    private corsOption: RequestOptionsArgs = {
        withCredentials: true
    };

    constructor(private http: Http) {
        console.log('UserProfileService construct');
    }

    getUserProfile(): Observable<UserProfile> {
        return this.http.get(this.HOST_PATH + this.BASE_URL + this.URL_SESSION, this.corsOption)
            .map((response: Response) => <UserProfile>response.json());
    }

    logOutUserProfile(): Observable<boolean> {
        return this.http.post(this.HOST_PATH + this.BASE_URL + this.URL_LOGOUT, {}, this.corsOption)
            .take(1)
            .map((response: Response) => response.ok);
    }
}

export { UserProfileService };