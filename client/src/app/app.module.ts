import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { UserProfileComponent, UserProfileService } from './user-profile';

@NgModule({
  imports: [BrowserModule, CommonModule, HttpModule],
  providers: [UserProfileService],
  declarations: [AppComponent, UserProfileComponent],
  bootstrap: [AppComponent]
})
class AppModule {
}

export { AppModule };
