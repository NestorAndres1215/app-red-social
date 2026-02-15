import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-oauth2-redirect',
  imports: [],
  templateUrl: './oauth2-redirect.html',
  styleUrl: './oauth2-redirect.css',
})
export class Oauth2Redirect {
 constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
    if (token) {
      this.authService.setToken(token);
      this.router.navigate(['/inicio'], { replaceUrl: true });
    } else {
      this.router.navigate(['/login']);
    }
  }
}
