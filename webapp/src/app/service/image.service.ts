import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../environments/environment";
import {ImageModel} from "../model/image.model";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { }

  addImage(image: ImageModel): Observable<ImageModel> {
    return this.http.post<ImageModel>(`${environment.apiUrl}/api/addImage`, image);
  }

  deleteImage(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/deleteImage/${id}`);
  }

  getImages(): Observable<ImageModel[]> {
    return this.http.get<ImageModel[]>(`${environment.apiUrl}/api/images`);
  }
}
