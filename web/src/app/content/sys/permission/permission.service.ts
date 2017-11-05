import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Permission} from "../../../models/permission";
import {GlobalVariables} from "../../../global-variables";

@Injectable()
export class PermissionService {

  private getUserMenusApi = GlobalVariables.API_BASE_URL + "user/menus";
  private permissionApi = GlobalVariables.API_BASE_URL + "sys/permissions";

  constructor(private http: HttpClient) {

  }

  public list(): Promise<Permission[]> {
    return this.http.get(this.permissionApi)
      .toPromise()
      .then(data => data as Permission[]);
  }


  public findUserMenus(): Promise<Permission[]> {
    return this.http.get(this.getUserMenusApi)
      .toPromise()
      .then(data => data as Permission[])
      .catch(reason => Promise.reject(reason));
  }

}
