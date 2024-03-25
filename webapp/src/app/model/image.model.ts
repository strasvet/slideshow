export class ImageModel {
  id?: number;
  url: string;
  duration: number;

  constructor(url: string, duration: number) {
    this.url = url;
    this.duration = duration;
  }
}
