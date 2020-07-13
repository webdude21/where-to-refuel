function nullSorter() {
  return 0;
}

function nullKeyExtractor() {
  return null;
}

function buildKeyExtractor(path) {
  if (path == null) {
    return nullKeyExtractor;
  }

  return function (object) {
    let propsInPath = path.split(".");

    for (let prop of propsInPath) {
      object = object[prop];
    }

    return object;
  }
}

export function getSorter(path, sampleObject) {
  let keyExtractor = buildKeyExtractor(path);
  let sortKey = keyExtractor(sampleObject);

  if (sortKey == null) {
    return nullSorter;
  }

  if (typeof sortKey === "string") {
    return stringSorter(keyExtractor);
  }

  if (typeof sortKey === "number") {
    return numberSorter(keyExtractor);
  }

  console.warn("Unexpected object passed -> ", sortKey);
}

function stringSorter(keyExtractor) {
  return function (a, b) {
    return keyExtractor(a).localeCompare(keyExtractor(b));
  }
}

function numberSorter(keyExtractor) {
  return function (a, b) {
    return keyExtractor(a) - keyExtractor(b);
  }
}
