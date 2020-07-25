Dog Breeds Identification:
This is a mobile application cabable of detecting 120 dog breeds.
Neural Network Summary:
The dataset used for trained was provided by Kaggle and consists of 9982 pictures, split into training and validation images. The image size was 224, the batch size used was 16, and the network was trained for 15 epochs.
The framework used for this application is keras. ImageDataGenerator is used for real-time data augumentation.
The Network Architecture is based on the pre-trained network MobileNetV2, using the ImageNet weights. At the head of the MobileNetV2, are two more Dense layers, for the app specific output.
The loss function used was 'categorical_crossentropy' and for the optimizer the use of 'adam' gave the best experimental results.
