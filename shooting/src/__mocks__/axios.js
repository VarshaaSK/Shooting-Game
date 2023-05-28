const mockData = {
    data : {
        // data : 100
        results : [
            {
                name : 'Sample',
                id : '01'
            }
        ]
    }
}

export default {
    get : jest.fn().mockResolvedValue(mockData)
}