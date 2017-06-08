import { ClientDemoPage } from './app.po';

describe('client-demo App', () => {
  let page: ClientDemoPage;

  beforeEach(() => {
    page = new ClientDemoPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
