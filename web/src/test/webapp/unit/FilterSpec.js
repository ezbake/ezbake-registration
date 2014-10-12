/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

'use strict';

describe('Registration filters', function(){
  beforeEach(module('registration.filters'));

  it('uriExample with prefix', inject(function(uriExampleFilter) {
    var webAppLink = {webUrl: "http://some.domain.org/cnn/{uri}", includePrefix: true};
    var actualUri = uriExampleFilter(webAppLink, "NEWS://cnn")
    var expectedUri = "http://some.domain.org/cnn/NEWS%3A%2F%2Fcnn%2F12345"
    expect(actualUri).toBe(expectedUri);
  }));

  it('uriExample without prefix', inject(function(uriExampleFilter) {
    var webAppLink = {webUrl: "http://some.domain.org/cnn/{uri}", includePrefix: false};
    var actualUri = uriExampleFilter(webAppLink, "NEWS://cnn")
    var expectedUri = "http://some.domain.org/cnn/12345"
    expect(actualUri).toBe(expectedUri);
  }));
});