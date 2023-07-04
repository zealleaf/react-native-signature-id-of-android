import { NativeModules } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-signature-id-of-android' doesn't seem to be linked. Make sure: \n\n` +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const SignatureIdOfAndroid = NativeModules.SignatureIdOfAndroid
  ? NativeModules.SignatureIdOfAndroid
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function getSignatureIdAsync(): Promise<string> {
  return SignatureIdOfAndroid.getSignatureIdAsync();
}
