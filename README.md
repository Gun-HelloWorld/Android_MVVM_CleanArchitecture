<h1 align="center">Android MVVM Clean Architecture</h1><br>
<p align="center">
MVVM & Clean Architecture 패턴을 적용하고 Marvel의 Public API를 통해 수집한 데이터를 표시하는 안드로이드 샘플 앱
</p>
<br>

## 화면
<table>  
  <th>Home</th>
  <tr>
    <td>
      <img src="https://github.com/Gun-HelloWorld/Android_MVVM_CleanArchitecture/assets/129313980/76e9aa27-1ab7-476f-842f-4e62aece9abf" width="250" height="555"/>
    </td>
  </tr>
</table>

<br>

## 적용 범위
- [x] 홈 화면 - 상단 로고 애니메이션, 배너, 카테고리별 아이템 표시
- [ ] 아이템 상세 화면 - 각 콘텐츠 상세화면 표시
- [ ] 카테고리 상세 화면 - 각 카테고리 전체 조회 상세화면 표시 (페이징 적용)
- [ ] 검색 화면 - 검색 결과 표시 (페이징 적용)
- [ ] 즐겨찾기 화면 - 로컬 기반 즐겨찾기 컬렉션 표시
- [ ] 프로필 화면 - 로컬 기반 프로필 관리 화면 표시

<br>

## 기술 스택 및 라이브러리
* [Kotlin](https://kotlinlang.org/)
  - 안드로이드 공식 프로그래밍 기본 언어입니다.
* [Coroutine](https://kotlinlang.org/docs/coroutines-overview.html) 
  - Android의 비동기 프로그래밍에 권장되는 솔루션이며 경량 스레드라고도 표현합니다.
* [Flow](https://kotlinlang.org/docs/flow.html) 
  - 여러 값을 순차적으로 방출하는 비동기 데이터 스트림을 지원합니다.
* [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) 
  - 초기값과 마지막 값을 보유하며 중복된 데이터를 발행하지 않아 주로 화면에 표시할 UI 데이터에 사용됩니다.
* [SharedFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-shared-flow/) 
  - 초기값과 마지막 값을 값을 보유하지 않고 중복된 데이터를 발행하여 주로 일회성 이벤트 액션에 사용됩니다.
* [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html#kotlin_dsl)
  - Gradle 스크립트 언어로, 자동완성 지원 및 가독성 확보 이점을 확보하기 위해 사용합니다.
* [Hilt](https://dagger.dev/hilt/)
  - Dagger2 기반 안드로이드 전용 DI 라이브러리입니다.
* [Retrofit2](https://square.github.io/retrofit/), [OkHttp3](https://square.github.io/okhttp/) 
  - REST API를 호출하기 위한 비동기 네트워크 처리를 단순화 시켜줍니다.
* [Gson](https://github.com/google/gson) 
  - Json 포맷의 요청/응답 데이터를 쉽게 파싱할 수 있도록 도와줍니다.
* [AAC-Navigation](https://developer.android.com/guide/navigation) 
  - Android 스튜디오의 Navigation Editor를 사용하여 앱의 탐색 그래프를 지원하며, Fragment 기반 화면 이동 처리를 도와줍니다.
* [AAC-DataBinding](https://developer.android.com/topic/libraries/data-binding?hl=ko)
  - View 바인딩 절차를 간소화하고 View 클래스에서 담당하는 로직을 xml 상에서 처리하여 View 클래스의 역할을 분담하도록 할 수 있습니다.
* [AAC-ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - 화면 회전 시 View가 재생성 되지만 관련 데이터가 소멸되지 않도록 보장하며, 사용자 이벤트를 처리하고 View에서 표시해야 할 데이터 및 상태를 관리합니다.
* [Glide](https://github.com/bumptech/glide)
  - 이미지를 빠르고 효율적으로 표시해 주는 것을 도와줍니다.
* [DotsIndicator](https://github.com/tommybuonomo/dotsindicator)
  - 뷰페이저 인디케이터를 쉽게 관리하도록 지원합니다.
* [CardView](https://developer.android.com/guide/topics/ui/layout/cardview)
  - 그림자, 테두리 변형 등 간편하게 스타일을 일관되게 유지하는 데 도움을 주는 라이브러리입니다.
* [ShimmerEffect](https://github.com/facebook/shimmer-android)
  - 비동기 처리 중 사용 시 애니메이션을 적용한 로딩 표시를 지원하여 UI 퍼포먼스를 올려줍니다.

<br>

## 아키텍처
* [MVVM](https://ko.wikipedia.org/wiki/%EB%AA%A8%EB%8D%B8-%EB%B7%B0-%EB%B7%B0%EB%AA%A8%EB%8D%B8)
  - 아키텍처 패턴 중 하나로, 데이터를 처리할 `모델(Model)`, 사용자에게 보여지는 UI인 `뷰(View)`, 뷰에 바인딩 되어 모델과 뷰 사이를 이어주는 `뷰 모델(View Model)`로 분리합니다.
* [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  - 관심사를 계층 구조로 분리하자는 목표를 가지며 이를 통해 프로젝트 규모가 커짐에 따라 유지보수성과 확장성을 높입니다.
* [Multi-Module](https://developer.android.com/topic/modularization)
  - 각 모듈을 독립적으로 관리하고 명시적인 모듈 종속성 선언 없이는 참조할 수 없도록 강력하게 접근성을 제한할 수 있습니다.
* [SAA (Single Activity Architecture)](https://www.youtube.com/watch?v=2k8x8V77CrU)
  - 하나 혹은 적은 개수의 `Activity`만을 사용하고 나머지 화면은 `Fragment`로 구성한 구조로, 주로 JetPack `Navigation`과 함께 사용합니다.
* [Repository Pattern](https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern)
  - `DataSource`를 캡슐화 시켜 `Data`의 출처와 관계 없이 동일한 인터페이스로 데이터에 접근할 수 있도록 도메인과 데이터 레이어들 사이를 중재 해주는 디자인 패턴입니다.
* [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - 의존성 주입은 확장 및 테스트 가능한 Android 앱을 만드는 데 유용한 기법이며, 이 프로젝트에서는 `Hilt`를 통해 이러한 의존 주입 프로세스를 자동화합니다.

<br>

## Multi-Module
* `buildsrc`
  - 멀티 모듈 특성상 나뉘어 있는 라이브러리 의존성을 한 곳에서 관리함으로써 통일성을 보장합니다.
* `presentation`
  - `Data`, `Domain` 모듈에 의존성을 가지고, 화면 처리와 관련된 일련의 작업을 담당하며 `View`, `ViewModel`이 이에 속합니다.
* `domain`
  - 어떠한 모듈에도 의존성을 가지지 않고, 비즈니스 로직을 관리하며 `UseCase`, `Repository`(인터페이스)가 이에 속합니다.
* `data`
  - `Domain` 계층에 의존성을 가지고, 네트워크나 로컬 DB의 데이터 접근을 담당하며 `Repository`(구현클래스), `DataSource`, `DTO`가 이에 속합니다.

<br>

## Marvel Comics API
해당 프로젝트는 [Marvel Comics API](https://developer.marvel.com/docs) 연동을 통해 데이터를 표시하며, 이 앱에서 사용되는 콘텐츠들의 저작권은 [Marvel](https://www.marvel.com)에 있습니다.

<br>

## 앱 빌드 시 필요한 필수 설정 항목
앱 빌드를 위해선 [Marvel의 개발자 포털](https://developer.marvel.com)에서 API Key를 발급받아 아래와 같은 설정이 필요합니다.
프로젝트 루트 디렉터리에 위치한 `local.properties` 파일에 아래와 같이 동일한 필드명에 API Key 정보를 입력해야 하며
프로젝트 빌드 시 `Data 모듈`에 생성되는 `BuildConfig`의  필드를 통해 앱에서 사용됩니다.

![image](https://github.com/Gun-HelloWorld/Android_MVVM_CleanArchitecture/assets/129313980/661b3995-115d-4f17-8aa3-0c5a7ea13f43)

<br>
