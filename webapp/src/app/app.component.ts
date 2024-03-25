import {Component, OnInit} from '@angular/core';
import {ImageModel} from "./model/image.model";
import {ImageService} from "./service/image.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  gotImages: ImageModel[] = [];

  currentImageIndex: number = 0;
  slideshowInterval: any;
  countdown: number = 0;
  newImageUrl: string = '';
  newImageDuration: number = 5;

  constructor(private imageService: ImageService) {
    //   this.imageService.getImages().subscribe(
    //     (next: ImageModel[]) => {
    //       console.log("received images")
    //       console.log(next)
    //     },
    //     (error: any) => {
    //       console.log("error occurred whily try got images")
    //     },
    //     () => {
    //       console.log("just complete method")
    //     }
    // );
  }

  ngOnInit(): void {
    this.startShow();
  }

  startShow(): void {
    this.imageService.getImages().subscribe((data: ImageModel[]) => {
      this.gotImages = data;
      this.startSlideshow();
    });
  }

  startSlideshow(): void {
    this.currentImageIndex = 0;
    this.showNextImage();
  }

  showNextImage(): void {
    if (this.gotImages.length === 0) {
      return;
    }

    this.currentImageIndex = (this.currentImageIndex + 1) % this.gotImages.length;
    const currentImage = this.gotImages[this.currentImageIndex];
    this.countdown = currentImage.duration; // Set countdown for the current image
    this.startCountdown();
  }

  startCountdown(): void {
    ``
    clearInterval(this.slideshowInterval);
    this.slideshowInterval = setInterval(() => {
      this.countdown--;
      if (this.countdown <= 0) {
        clearInterval(this.slideshowInterval);
        this.showNextImage();
      }
    }, 1000);
  }


  pauseSlideshow(): void {
    clearInterval(this.slideshowInterval);
  }

  addImage(): void {
    if (this.newImageUrl.trim() !== '') {
      const newImage = new ImageModel(this.newImageUrl, this.newImageDuration);
      this.imageService.addImage(newImage).subscribe((data: ImageModel) => {
        this.gotImages.push(data);
        this.newImageUrl = '';
        this.newImageDuration = 5;

        if (this.gotImages.length === 1) {
          clearInterval(this.slideshowInterval);
          this.showNextImage();
        }
        ;
      });

    } else {
      console.log('Invalid URL');
    }

  }

  deleteImage(id: number): void {
    this.imageService.deleteImage(id).subscribe(
      () => {
        if (this.gotImages[this.currentImageIndex]?.id === id) {
          this.pauseSlideshow();
        }
        this.gotImages = this.gotImages.filter(image => image.id !== id);

        if (this.currentImageIndex >= this.gotImages.length) {
          this.currentImageIndex = 0;
        }

      },
      (error) => {
        console.error('error deleting image:', error);
      }
    );
  }
}
