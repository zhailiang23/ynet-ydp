/**
 * 地理工具函数
 */

/**
 * 生成圆形 GeoJSON Polygon（通过多边形近似）
 * @param centerLng 中心经度
 * @param centerLat 中心纬度
 * @param radiusMeters 半径（米）
 * @param sides 边数（默认 64，边数越多越接近圆形）
 * @returns GeoJSON Polygon 对象
 */
export function generateCirclePolygon(
  centerLng: number,
  centerLat: number,
  radiusMeters: number,
  sides: number = 64,
): GeoJSON.Polygon {
  const coordinates: [number, number][] = [];
  const earthRadiusMeters = 6_371_000; // 地球半径（米）

  // 计算纬度和经度的弧度变化
  const radiusLat = (radiusMeters / earthRadiusMeters) * (180 / Math.PI);
  const radiusLng = radiusLat / Math.cos((centerLat * Math.PI) / 180);

  // 生成 N 个点形成圆形
  for (let i = 0; i < sides; i++) {
    const angle = (i / sides) * 2 * Math.PI;
    const lng = centerLng + radiusLng * Math.cos(angle);
    const lat = centerLat + radiusLat * Math.sin(angle);
    coordinates.push([lng, lat]);
  }

  // 闭合多边形
  coordinates.push(coordinates[0]);

  return {
    type: 'Polygon',
    coordinates: [coordinates],
  };
}

/**
 * 将 GeoJSON Polygon 转换为 JSON 字符串
 */
export function geoJsonToString(geoJson: GeoJSON.Polygon): string {
  return JSON.stringify(geoJson);
}

/**
 * 解析 GeoJSON 字符串
 */
export function parseGeoJson(geoJsonString: string): GeoJSON.Polygon | null {
  try {
    return JSON.parse(geoJsonString);
  } catch {
    return null;
  }
}
