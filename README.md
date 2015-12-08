# vunits

## Add repository to project build file
```javascript
  repositories {
	    // ...
	    maven { url "https://jitpack.io" }
	}
```

## Add dependency to your module.

```javascript
  	dependencies {
	        compile 'com.github.vaslabs:vunits:0.3'
	}
```

# Units supported:

## Distance
* Meters
* Feet
* Kilometers
* Miles

## Time
* Milliseconds
* Seconds
* Minutes
* Hours
* Days

## Velocity
Combine any one from distance with one from time

## Pressure

### Tested
* PSI
* ATM
* hPa

### Untested
* Pascal
* Torr
* AT
