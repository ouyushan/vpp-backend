## 基于DDD的虚拟电厂后端服务

| 模块 | 描述  |   |
|----|-----|---|
|  masterdata  | 主数据 |   |
|  load  |     |   |   |
|  production  |     |   |
|  action  |     |   |


## http://localhost:8081/masterdata/api/

```json
{
    "_links": {
        "windEnergies": {
            "href": "http://localhost:8081/masterdata/api/windEnergies{?page,size,sort}",
            "templated": true
        },
        "virtualPowerPlants": {
            "href": "http://localhost:8081/masterdata/api/virtualPowerPlants{?page,size,sort}",
            "templated": true
        },
        "decentralizedPowerPlants": {
            "href": "http://localhost:8081/masterdata/api/decentralizedPowerPlants{?page,size,sort}",
            "templated": true
        },
        "storages": {
            "href": "http://localhost:8081/masterdata/api/storages{?page,size,sort}",
            "templated": true
        },
        "waterEnergies": {
            "href": "http://localhost:8081/masterdata/api/waterEnergies{?page,size,sort}",
            "templated": true
        },
        "otherEnergies": {
            "href": "http://localhost:8081/masterdata/api/otherEnergies{?page,size,sort}",
            "templated": true
        },
        "solarEnergies": {
            "href": "http://localhost:8081/masterdata/api/solarEnergies{?page,size,sort}",
            "templated": true
        },
        "households": {
            "href": "http://localhost:8081/masterdata/api/households{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8081/masterdata/api/profile"
        }
    }
}
```